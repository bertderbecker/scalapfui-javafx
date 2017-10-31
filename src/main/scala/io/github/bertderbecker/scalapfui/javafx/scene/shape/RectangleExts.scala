package io.github.bertderbecker.scalapfui.javafx.scene.shape

import javafx.beans.property.Property
import javafx.scene.shape.{Rectangle => JFXRectangle}

import io.github.bertderbecker.scalapfui.attribute.{Attribute, ReadableAttribute}
import io.github.bertderbecker.scalapfui.javafx.Implicits._
import io.github.bertderbecker.scalapfui.javafx.FXElementTag
import io.github.bertderbecker.scalapfui.javafx.attribute.{FXAttribute, FXReadableAttribute}

import scala.language.implicitConversions

//Ready
object RectangleExts {

  val Rectangle: FXElementTag[JFXRectangle] = FXElementTag(() => new JFXRectangle())

  val arcHeight: Attribute[Double, JFXRectangle] =
    FXAttribute[java.lang.Double, JFXRectangle](_.arcHeightProperty().asObject())

  val arcWidth: Attribute[Double, JFXRectangle] =
    FXAttribute[java.lang.Double, JFXRectangle](_.arcWidthProperty().asObject())

  val x: Attribute[Double, JFXRectangle] =
    FXAttribute[java.lang.Double, JFXRectangle](_.xProperty().asObject())

  val y: Attribute[Double, JFXRectangle] =
    FXAttribute[java.lang.Double, JFXRectangle](_.yProperty().asObject())


  trait SharedAttributes {

    val height: ReadableAttribute[Double, JFXRectangle] =
      FXReadableAttribute[java.lang.Double, JFXRectangle](_.heightProperty().asObject())

    val width: ReadableAttribute[Double, JFXRectangle] =
      FXReadableAttribute[java.lang.Double, JFXRectangle](_.widthProperty().asObject())

  }

  object rectangle extends SharedAttributes

}
