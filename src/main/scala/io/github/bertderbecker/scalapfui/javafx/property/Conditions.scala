package io.github.bertderbecker.scalapfui.javafx.property

import io.github.bertderbecker.scalapfui.javafx.Implicits
import io.github.bertderbecker.scalapfui.property.ReadableProperty

object Conditions {

  case class Condition[T](prop: ReadableProperty[Boolean]) {

    def choose(one: T) = PartialBinding(this, one)

  }

  case class PartialBinding[T](condition: Condition[T], one: T) {

    def otherwise(other: T): ReadableProperty[T] =
      Implicits.readablePropertyMonad.map(condition.prop)(cond => if (cond) one else other)

  }

  def when[T](prop: ReadableProperty[Boolean]): Condition[T] = Condition(prop)

}
