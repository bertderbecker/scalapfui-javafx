package io.github.bertderbecker.scalapfui.javafx

import javafx.scene.{Parent => JFXParent}

import cats.functor.Invariant
import io.github.bertderbecker.scalapfui.Math.MathInstances
import io.github.bertderbecker.scalapfui.attribute.{Attribute, ReadableAttribute}
import io.github.bertderbecker.scalapfui.extras.Includes
import io.github.bertderbecker.scalapfui.javafx.attribute.FXReadableAttribute.ReadableAttributeInstances
import io.github.bertderbecker.scalapfui.javafx.attribute.StoredReadableAttributeInstances
import io.github.bertderbecker.scalapfui.javafx.geometry.Insets
import io.github.bertderbecker.scalapfui.javafx.geometry.Insets.InsetInstances
import io.github.bertderbecker.scalapfui.javafx.property.FXProperty.PropertyInstances
import io.github.bertderbecker.scalapfui.javafx.property.FXReadableProperty.ReadablePropertyInstances
import io.github.bertderbecker.scalapfui.javafx.scene.control.LabelExts.LabelInstances
import io.github.bertderbecker.scalapfui.property.{Property, ReadableProperty}

import scala.language.{higherKinds, implicitConversions}

object Implicits
  extends ReadablePropertyInstances
    with ReadableAttributeInstances
    with StoredReadableAttributeInstances
    with PropertyInstances
    with MathInstances
    with LabelInstances
    with InsetInstances
    with Includes {


  implicit def covariantElement2seq[CC[N] <: FXElement[N], T](x: CC[_ <: T]): Seq[CC[_ <: T]] = Seq(x)

  implicit def jDoubleReadableAttr2scalaDoubleReadableAttr[N](
                                                               jattr: ReadableAttribute[java.lang.Double, N]
                                                             ): ReadableAttribute[Double, N] =
    new ReadableAttribute[Double, N] {
      override val readablePropertyExtr: N => ReadableProperty[scala.Double] =
        native => readablePropertyMonad.map(jattr.readablePropertyExtr(native))(d => Double.box(d))
    }

  implicit def jBooleanReadableAttr2scalaBooleanReadableAttr[N](
                                                                 jattr: ReadableAttribute[java.lang.Boolean, N]
                                                               ): ReadableAttribute[Boolean, N] =
    new ReadableAttribute[Boolean, N] {
      override val readablePropertyExtr: N => ReadableProperty[scala.Boolean] =
        native => readablePropertyMonad.map(jattr.readablePropertyExtr(native))(d => Boolean.box(d))
    }


  implicit def jIntReadableAttr2scalaIntReadableAttr[N](
                                                         jattr: ReadableAttribute[java.lang.Integer, N]
                                                       ): ReadableAttribute[Int, N] =
    new ReadableAttribute[Int, N] {
      override val readablePropertyExtr: N => ReadableProperty[scala.Int] =
        native => readablePropertyMonad.map(jattr.readablePropertyExtr(native))(d => Int.box(d))
    }


  implicit def jLongReadableAttr2scalaLongReadableAttr[N](
                                                           jattr: ReadableAttribute[java.lang.Long, N]
                                                         ): ReadableAttribute[Long, N] =
    new ReadableAttribute[Long, N] {
      override val readablePropertyExtr: N => ReadableProperty[scala.Long] =
        native => readablePropertyMonad.map(jattr.readablePropertyExtr(native))(d => Long.box(d))
    }


  implicit def jByteReadableAttr2scalaByteReadableAttr[N](
                                                           jattr: ReadableAttribute[java.lang.Byte, N]
                                                         ): ReadableAttribute[Byte, N] =
    new ReadableAttribute[Byte, N] {
      override val readablePropertyExtr: N => ReadableProperty[scala.Byte] =
        native => readablePropertyMonad.map(jattr.readablePropertyExtr(native))(d => Byte.box(d))
    }


  implicit def jCharReadableAttr2scalaCharReadableAttr[N](
                                                           jattr: ReadableAttribute[java.lang.Character, N]
                                                         ): ReadableAttribute[Char, N] =
    new ReadableAttribute[Char, N] {
      override val readablePropertyExtr: N => ReadableProperty[scala.Char] =
        native => readablePropertyMonad.map(jattr.readablePropertyExtr(native))(d => Char.box(d))
    }


  implicit def jFloatReadableAttr2scalaFloatReadableAttr[N](
                                                             jattr: ReadableAttribute[java.lang.Float, N]
                                                           ): ReadableAttribute[Float, N] =
    new ReadableAttribute[Float, N] {
      override val readablePropertyExtr: N => ReadableProperty[scala.Float] =
        native => readablePropertyMonad.map(jattr.readablePropertyExtr(native))(d => Float.box(d))
    }


  implicit def jShortReadableAttr2scalaShortReadableAttr[N](
                                                             jattr: ReadableAttribute[java.lang.Short, N]
                                                           ): ReadableAttribute[Short, N] =
    new ReadableAttribute[Short, N] {
      override val readablePropertyExtr: N => ReadableProperty[scala.Short] =
        native => readablePropertyMonad.map(jattr.readablePropertyExtr(native))(d => Short.box(d))
    }


  implicit def jInsetsReadableAttr2scalaInsetsReadableAttr[N](
                                                               jattr: ReadableAttribute[javafx.geometry.Insets, N]
                                                             ): ReadableAttribute[Insets, N] =
    new ReadableAttribute[Insets, N] {
      override val readablePropertyExtr: N => ReadableProperty[Insets] =
        native => readablePropertyMonad.map(jattr.readablePropertyExtr(native))(jFXInsets2Insets)
    }


  implicit def jDoubleAttr2scalaDoubleAttr[N](
                                               jattr: Attribute[java.lang.Double, N]
                                             ): Attribute[Double, N] =
    new Attribute[Double, N] {
      override val propertyExtr: N => Property[scala.Double] =
        native => jDoubleProperty2scalaProperty(jattr.propertyExtr(native))
    }


  implicit def jBooleanAttr2scalaBooleanAttr[N](
                                                 jattr: Attribute[java.lang.Boolean, N]
                                               ): Attribute[Boolean, N] =
    new Attribute[Boolean, N] {
      override val propertyExtr: N => Property[scala.Boolean] =
        native => jBooleanProperty2scalaProperty(jattr.propertyExtr(native))
    }


  implicit def jIntAttr2scalaIntAttr[N](
                                         jattr: Attribute[java.lang.Integer, N]
                                       ): Attribute[Int, N] =
    new Attribute[Int, N] {
      override val propertyExtr: N => Property[scala.Int] =
        native => jIntProperty2scalaProperty(jattr.propertyExtr(native))
    }


  implicit def jLongAttr2scalaLongAttr[N](
                                           jattr: Attribute[java.lang.Long, N]
                                         ): Attribute[Long, N] =
    new Attribute[Long, N] {
      override val propertyExtr: N => Property[scala.Long] =
        native => jLongProperty2scalaProperty(jattr.propertyExtr(native))
    }


  implicit def jByteAttr2scalaByteAttr[N](
                                           jattr: Attribute[java.lang.Byte, N]
                                         ): Attribute[Byte, N] =
    new Attribute[Byte, N] {
      override val propertyExtr: N => Property[scala.Byte] =
        native => jByteProperty2scalaProperty(jattr.propertyExtr(native))
    }


  implicit def jCharAttr2scalaCharAttr[N](
                                           jattr: Attribute[java.lang.Character, N]
                                         ): Attribute[Char, N] =
    new Attribute[Char, N] {
      override val propertyExtr: N => Property[scala.Char] =
        native => jCharProperty2scalaProperty(jattr.propertyExtr(native))
    }


  implicit def jFloatAttr2scalaFloatAttr[N](
                                             jattr: Attribute[java.lang.Float, N]
                                           ): Attribute[Float, N] =
    new Attribute[Float, N] {
      override val propertyExtr: N => Property[scala.Float] =
        native => jFloatProperty2scalaProperty(jattr.propertyExtr(native))
    }


  implicit def jShortAttr2scalaShortAttr[N](
                                             jattr: Attribute[java.lang.Short, N]
                                           ): Attribute[Short, N] =
    new Attribute[Short, N] {
      override val propertyExtr: N => Property[scala.Short] =
        native => jShortProperty2scalaProperty(jattr.propertyExtr(native))
    }


  implicit def jInsetsAttr2scalaInsetsAttr[N](
                                               jattr: Attribute[javafx.geometry.Insets, N]
                                             ): Attribute[Insets, N] =
    new Attribute[Insets, N] {
      override val propertyExtr: N => Property[Insets] =
        native => jInsetsProperty2scalaProperty(jattr.propertyExtr(native))
    }


  implicit def jDoubleProperty2scalaProperty(jprop: Property[java.lang.Double]): Property[scala.Double] =
    propertyInvariant.imap[java.lang.Double, scala.Double](jprop)(Double.box(_))(java.lang.Double.valueOf)

  implicit def jBooleanProperty2scalaProperty(jprop: Property[java.lang.Boolean]): Property[scala.Boolean] =
    propertyInvariant.imap[java.lang.Boolean, scala.Boolean](jprop)(Boolean.box(_))(java.lang.Boolean.valueOf)

  implicit def jIntProperty2scalaProperty(jprop: Property[java.lang.Integer]): Property[scala.Int] =
    propertyInvariant.imap[java.lang.Integer, scala.Int](jprop)(Int.box(_))(java.lang.Integer.valueOf)

  implicit def jLongProperty2scalaProperty(jprop: Property[java.lang.Long]): Property[scala.Long] =
    propertyInvariant.imap[java.lang.Long, scala.Long](jprop)(Long.box(_))(java.lang.Long.valueOf)

  implicit def jByteProperty2scalaProperty(jprop: Property[java.lang.Byte]): Property[scala.Byte] =
    propertyInvariant.imap[java.lang.Byte, scala.Byte](jprop)(Byte.box(_))(java.lang.Byte.valueOf)

  implicit def jCharProperty2scalaProperty(jprop: Property[java.lang.Character]): Property[scala.Char] =
    propertyInvariant.imap[java.lang.Character, scala.Char](jprop)(Char.box(_))(java.lang.Character.valueOf)

  implicit def jFloatProperty2scalaProperty(jprop: Property[java.lang.Float]): Property[scala.Float] =
    propertyInvariant.imap[java.lang.Float, scala.Float](jprop)(Float.box(_))(java.lang.Float.valueOf)

  implicit def jShortProperty2scalaProperty(jprop: Property[java.lang.Short]): Property[scala.Short] =
    propertyInvariant.imap[java.lang.Short, scala.Short](jprop)(Short.box(_))(java.lang.Short.valueOf)

  implicit def jInsetsProperty2scalaProperty(jprop: Property[javafx.geometry.Insets]): Property[geometry.Insets] =
    propertyInvariant
      .imap[javafx.geometry.Insets, geometry.Insets](jprop)(
      i => geometry.Insets.apply(i.getTop, i.getRight, i.getBottom, i.getLeft))(
      i => new javafx.geometry.Insets(i.top, i.right, i.bottom, i.left))


  implicit def renderFXElement[N](fxelement: FXElement[N]): N = fxelement.render

  implicit def renderFXParent[N <: JFXParent](fxparent: FXParent[N]): N = fxparent.render


  implicit class InvariantImplicits[F[_] <: AnyRef : Invariant, A](x: F[A]) {
    def imap[B](f: (A) ⇒ B)(g: (B) ⇒ A): F[B] =
      implicitly[Invariant[F]].imap(x)(f)(g)
  }

}
