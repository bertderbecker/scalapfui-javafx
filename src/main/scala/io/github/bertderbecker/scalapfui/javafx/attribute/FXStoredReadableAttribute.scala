package io.github.bertderbecker.scalapfui.javafx.attribute

import javafx.beans.property.SimpleObjectProperty

import cats.Functor
import io.github.bertderbecker.scalapfui.attribute.StoredReadableAttribute
import io.github.bertderbecker.scalapfui.javafx.Implicits
import io.github.bertderbecker.scalapfui.javafx.property.FXProperty
import io.github.bertderbecker.scalapfui.property.Property

import scala.language.implicitConversions

case class FXStoredReadableAttribute[T]() extends StoredReadableAttribute[T] {

  override val property: Property[T] = FXProperty.apply(new SimpleObjectProperty[T]())

}

trait StoredReadableAttributeInstances {

  implicit def storedReadableAttributeFunctor: Functor[StoredReadableAttribute] =
    new Functor[StoredReadableAttribute] {

      override def map[A, B](fa: StoredReadableAttribute[A])
                            (f: A => B): StoredReadableAttribute[B] = {
        val res = new StoredReadableAttribute[B] {
          override val property: Property[B] =
            Implicits.propertyInvariant.imap(fa.property)(f)(_ =>
              throw new IllegalAccessException("this mapping function does not support backpressuring"))
        }
        res
      }
    }

}
