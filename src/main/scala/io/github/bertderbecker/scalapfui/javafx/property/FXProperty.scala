package io.github.bertderbecker.scalapfui.javafx.property

import javafx.beans.property.{SimpleObjectProperty, Property => JFXProperty}

import cats.functor.Invariant
import io.github.bertderbecker.scalapfui.extras.Includes._
import io.github.bertderbecker.scalapfui.property._

import scala.language.higherKinds

case class FXProperty[T](jFXProperty: JFXProperty[T])
  extends NestedSplitProperty[T] {


  override def doBidirectionalBinding(other: Property[T]): Unit = {

    other.value.ifDefined(update)

    other match {
      case otherNested: FXProperty[T] =>
        jFXProperty.bindBidirectional(otherNested.jFXProperty)
      case _ => super.bindBidirectional(other)
    }

  }

  override val writableProperty: WritableProperty[T] = FXWritableProperty(jFXProperty)
  override val readableProperty: ReadableProperty[T] = FXReadableProperty(jFXProperty)

}

object FXProperty {

  def apply[T](jFXProperty: => JFXProperty[T]): Property[T] = {
    println("begin FXProperty.apply(_ : => JFXProperty")
    val res = new FXProperty(jFXProperty)
    println("finish FXProperty.apply(_ : => JFXProperty")
    res
  }

  def apply[T](x: T): Property[T] = {
    println("begin FXProperty.apply(_ : T")
    val res = apply(new SimpleObjectProperty[T](x))
    println("finish FXProperty.apply(_ : T")
    res
  }


  trait PropertyInstances {

    //WITH BACKPRESSURE!!!!! YEAH!
    implicit val propertyInvariant: Invariant[Property] = new Invariant[Property] {
      override def imap[A, B](fa: Property[A])(f: (A) => B)(g: (B) => A): Property[B] = new Property[B] {

        override def doBidirectionalBinding(other: Property[B]): Unit =
          fa.doBidirectionalBinding(propertyInvariant.imap(other)(g)(f))

        override def doUpdate(newValue: B): Unit = fa.doUpdate(g(newValue))

        override def removeOnChange(op: (ReadableProperty[B], B, B) => Unit): Unit =
          fa.removeOnChange((ao, aov, anv) => op(self, f(aov), f(anv)))

        override def calcValue: Option[B] = fa.value.map(f)

        override def processOnChange(op: (ReadableProperty[B], B, B) => Unit): Unit =
          fa.processOnChange((ao, aov, anv) => op(self, f(aov), f(anv)))
      }
    }
  }

}
