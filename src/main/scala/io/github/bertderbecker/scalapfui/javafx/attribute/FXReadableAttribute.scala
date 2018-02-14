package io.github.bertderbecker.scalapfui.javafx.attribute

import javafx.beans.value.ObservableValue

import cats.{FlatMap, Functor}
import io.github.bertderbecker.scalapfui.attribute.ReadableAttribute
import io.github.bertderbecker.scalapfui.javafx.Implicits
import io.github.bertderbecker.scalapfui.javafx.property.FXReadableProperty
import io.github.bertderbecker.scalapfui.property.ReadableProperty

import scala.language.implicitConversions

object FXReadableAttribute
  extends AttributeCompanion[ReadableAttribute, ObservableValue] {

  override def apply[T, Native](propertyExtractor: (Native) => ObservableValue[T]): ReadableAttribute[T, Native] =
    new ReadableAttribute[T, Native] {
      override val readablePropertyExtr: Native => ReadableProperty[T] =
        native => FXReadableProperty(propertyExtractor(native))
    }

  trait ReadableAttributeInstances {

    implicit def readableProperty2readableAttribute[T, Native](readableProperty: ReadableProperty[T]
                                                              ): ReadableAttribute[T, Native] =
      new ReadableAttribute[T, Native] {
        override val readablePropertyExtr: (Native) => ReadableProperty[T] = Native => readableProperty
      }

    implicit def readableAttributeFunctor[Native]: Functor[ReadableAttribute[?, Native]] =
      new Functor[ReadableAttribute[?, Native]] {

        override def map[A, B](fa: ReadableAttribute[A, Native])
                              (f: A => B): ReadableAttribute[B, Native] = {
          val res = new ReadableAttribute[B, Native] {
            override val readablePropertyExtr: Native => ReadableProperty[B] =
              native => Implicits.readablePropertyMonad.map(fa.readablePropertyExtr(native))(f)
          }
          res
        }
      }

    implicit def readableAttributeFlatMap[Native]: FlatMap[ReadableAttribute[?, Native]] =
      new FlatMap[ReadableAttribute[?, Native]] {

        override def map[A, B](fa: ReadableAttribute[A, Native])(f: (A) => B): ReadableAttribute[B, Native] =
          readableAttributeFunctor[Native].map(fa)(f)

        override def tailRecM[A, B](a: A)(f: (A) => ReadableAttribute[Either[A, B], Native]): ReadableAttribute[B, Native] =
          new ReadableAttribute[B, Native] {
            override val readablePropertyExtr: (Native) => ReadableProperty[B] =
              native => Implicits.readablePropertyMonad.tailRecM(a)(aP => f(aP).readablePropertyExtr(native))
          }

        override def flatMap[A, B](fa: ReadableAttribute[A, Native])
                                  (f: (A) => ReadableAttribute[B, Native]): ReadableAttribute[B, Native] = {
          new ReadableAttribute[B, Native] {

            override val readablePropertyExtr: (Native) => ReadableProperty[B] =
              native =>
                Implicits.readablePropertyMonad
                  .flatMap(fa.readablePropertyExtr(native))(a => f(a).readablePropertyExtr(native))
          }
        }
      }
  }

}
