package io.github.bertderbecker.scalapfui.javafx.property

import io.github.bertderbecker.scalapfui.attribute.ReadableAttribute
import io.github.bertderbecker.scalapfui.javafx.Implicits
import io.github.bertderbecker.scalapfui.property.ReadableProperty

import scala.language.higherKinds

object Conditions {

  trait Condition[+A, Result[_], PB[C] <: PartialBinding[C, Result]] {
    def choose[B >: A](one: B): PB[B]
  }

  case class PropCondition[+A](prop: ReadableProperty[Boolean])
    extends Condition[A, ReadableProperty, PropPartialBinding] {

    def choose[B >: A](one: B): PropPartialBinding[B] = PropPartialBinding(this, one)

  }

  case class AttrCondition[+A, N](prop: ReadableAttribute[Boolean, N])
    extends Condition[A, ReadableAttribute[?, N], AttrPartialBinding[?, N]] {

    def choose[B >: A](one: B) = AttrPartialBinding(this, one)

  }

  trait PartialBinding[+A, Result[_]] {

    def otherwise[B >: A](other: B): Result[B]

  }

  case class PropPartialBinding[+A](condition: PropCondition[A], one: A)
    extends PartialBinding[A, ReadableProperty] {

    def otherwise[B >: A](other: B): ReadableProperty[B] =
      Implicits.readablePropertyMonad.map(condition.prop)(cond => if (cond) one else other)

  }

  case class AttrPartialBinding[+A, N](condition: AttrCondition[A, N], one: A)
    extends PartialBinding[A, ReadableAttribute[?, N]] {

    def otherwise[B >: A](other: B): ReadableAttribute[B, N] =
      Implicits.readableAttributeFlatMap.map(condition.prop)(cond => if (cond) one else other)

  }

  def when[T](prop: ReadableProperty[Boolean]): PropCondition[T] = PropCondition(prop)
  def when[T, N](prop: ReadableAttribute[Boolean, N]): AttrCondition[T, N] = AttrCondition(prop)

}
