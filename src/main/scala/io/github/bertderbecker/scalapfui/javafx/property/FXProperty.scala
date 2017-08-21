package io.github.bertderbecker.scalapfui.javafx.property

import javafx.beans.property.{SimpleObjectProperty, Property => JFXProperty}
import javafx.beans.value.ObservableValue

import cats.functor.Invariant
import io.github.bertderbecker.scalapfui.extras.Includes._
import io.github.bertderbecker.scalapfui.property._

import scala.language.higherKinds

case class FXProperty[T](jFXProperty: JFXProperty[T])
  extends NestedProperty[T, ObservableValue] {


  override def doBidirectionalBinding(other: Property[T]): Unit = {

    other.value.ifDefined(update)

    other match {
      case otherNested: FXProperty[T] =>
        jFXProperty.bindBidirectional(otherNested.jFXProperty)
      case _ =>
        val listener: (ReadableProperty[T], T, T) => Unit =
          BidirectionalBinding(this, other).listener
        this.onChangeFull(listener)
        other.onChangeFull(listener)
    }

  }

  override val nestedWritableProperty: NestedWritableProperty[T] = FXWritableProperty(jFXProperty)
  override val nestedReadableProperty: NestedReadableProperty[T, ObservableValue] = FXReadableProperty(jFXProperty)

}

object FXProperty {

  def apply[T](jFXProperty: => JFXProperty[T]): Property[T] = new FXProperty(jFXProperty)

  def apply[T](x: T): Property[T] = apply(new SimpleObjectProperty[T](x))


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

    /*

    //DOES NOT BACKPRESSURING!!! IF THE NEWER PROP IS UPDATED, LISTENERS FROM THE OLDER PROP ARE NOT CALLED!!!
    implicit val propertyFunctor: Functor[Property] =
      new Functor[Property] {
        override def map[A, B](fa: Property[A])(f: A => B): Property[B] =
          FXProperty[B]({
            val prop = fa.value match {
              case Some(v) => new SimpleObjectProperty[B](f(v))
              case _ => new SimpleObjectProperty[B]()
            }
            fa.onChange(newVal => prop.set(f(newVal)))
            prop
          })

      }

    //DOES NOT BACKPRESSURING!!! IF THE NEWER PROP IED, LISTENERS FROM THE OLDER PROP ARE NOT CALLED!!!
    implicit val propertyFlatMap: FlatMap[Property] = new FlatMap[Property] {

      override def flatMap[A, B](fa: Property[A])(f: (A) => Property[B]): Property[B] = {
        def bprop: Property[Property[B]] = propertyFunctor.map(fa)(f)

        new FXProperty[B](
          bprop.value.flatMap(_.value) match {
            case Some(v) => new SimpleObjectProperty[B](v)
            case _ => new SimpleObjectProperty[B]()
          }
        ) {

          bprop.onChange { newProp =>
            newProp.onChange(jFXProperty.setValue)
            newProp.value match {
              case Some(v) => jFXProperty.setValue(v)
              case _ =>
            }
          }

          bprop.onChange(_.onChange(this.update))

          override def removeOnChange(op: (ReadableProperty[B], B, B) => Unit): Unit =
            try {
              super.removeOnChange((o, ov, nv) => {
                bprop.removeOnChange((ao, aov, anv) => try {
                  anv.removeOnChange(op)
                } catch {
                  case e: Exception =>
                })
              })
            } catch {
              case e: Exception =>
            }

          override def processOnChange(op: (ReadableProperty[B], B, B) => Unit): Unit = {
            super.processOnChange((o, ov, nv) => {
              bprop.onChangeFull((ao, aov, anv) => anv.onChangeFull(op))
              op(o, ov, nv)
            })
          }
        }
      }

      override def tailRecM[A, B](a: A)(f: (A) => Property[Either[A, B]]): Property[B] = {
        throw new IllegalArgumentException("Operation not supported")
        flatMap(f(a)) {
          case Right(b) => FXProperty(b)
          case Left(nextA: A) => tailRecM(nextA)(f)
        }
      }

      override def map[A, B](fa: Property[A])(f: (A) => B): Property[B] = propertyFunctor.map(fa)(f)
    }

    */
  }

}
