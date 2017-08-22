package io.github.bertderbecker.scalapfui.javafx.property

import javafx.beans.property.SimpleObjectProperty
import javafx.beans.value.{ChangeListener, ObservableValue}

import cats.{Functor, Monad}
import io.github.bertderbecker.scalapfui.extras.Includes._
import io.github.bertderbecker.scalapfui.property.{NestedReadableProperty, ReadableProperty}

import scala.collection.mutable


case class FXReadableProperty[T](property: ObservableValue[T])
  extends NestedReadableProperty[T, ObservableValue] {

  val onChangeListeners: mutable.HashMap[(ReadableProperty[T], T, T) => Unit, ChangeListener[T]] = mutable.HashMap.empty

  override def processOnChange(op: (ReadableProperty[T], T, T) => Unit): Unit = {

    val listener = new ChangeListener[T] {
      override def changed(observable: ObservableValue[_ <: T], oldValue: T, newValue: T): Unit =
        op(FXReadableProperty.this, oldValue, newValue)
    }

    onChangeListeners += op -> listener
    property.addListener(listener)
  }

  override def calcValue: Option[T] = Option(property.getValue)

  override def removeOnChange(op: (ReadableProperty[T], T, T) => Unit): Unit = property.removeListener(onChangeListeners(op))

}


object FXReadableProperty {

  trait ReadablePropertyInstances {

    /* val b = readablePropertyFunctor.map(a)(fa)
     * When a changes, b changes too, b is like a forwarder for a.
     */


    implicit private val readablePropertyFunctor: Functor[ReadableProperty] =
      new Functor[ReadableProperty] {
        override def map[A, B](fa: ReadableProperty[A])(f: A => B): ReadableProperty[B] =
          new ReadableProperty[B] {

            override def processOnChange(op: (ReadableProperty[B], B, B) => Unit): Unit = {
              fa.processOnChange((_, oldVal, newVal) => op(self, f(oldVal), f(newVal)))
            }

            override def calcValue: Option[B] = fa.calcValue.map(f)

            override def removeOnChange(op: (ReadableProperty[B], B, B) => Unit): Unit = {
              fa.removeOnChange((_, oldVal, newVal) => op(self, f(oldVal), f(newVal)))
            }

          }

      }


    implicit val readablePropertyMonad: Monad[ReadableProperty] = new Monad[ReadableProperty] {


      /*
      override def map[A, B](fa: ReadableProperty[A])(f: (A) => B): ReadableProperty[B] =
        readablePropertyFunctor.map(fa)(f)
        */


      override def tailRecM[A, B](a: A)(f: (A) => ReadableProperty[Either[A, B]]): ReadableProperty[B] =
        flatMap(f(a)) {
          case Right(b) => pure(b)
          case Left(nextA) => tailRecM(nextA)(f)
        }

      override def flatMap[A, B](fa: ReadableProperty[A])(f: (A) => ReadableProperty[B]): ReadableProperty[B] = {

        def bprop: ReadableProperty[ReadableProperty[B]] = readablePropertyFunctor.map(fa)(f)

        new FXProperty[B](
          bprop
            .value
            .flatMap(_.value)
            .map(new SimpleObjectProperty[B](_))
            .getOrElse(new SimpleObjectProperty[B]())
        ) {

          bprop.onChange { newProp =>
            newProp.onChange(jFXProperty.setValue)
            newProp.value.ifDefined(jFXProperty.setValue)
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

      override def pure[A](x: A): ReadableProperty[A] = FXReadableProperty(new SimpleObjectProperty[A](x))
    }
  }


}