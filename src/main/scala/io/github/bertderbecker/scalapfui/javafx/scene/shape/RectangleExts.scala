package io.github.bertderbecker.scalapfui.javafx.scene.shape

import javafx.scene.shape.{Rectangle => JFXRectangle}

import io.github.bertderbecker.scalapfui.attribute.{Attribute, ReadableAttribute}
import io.github.bertderbecker.scalapfui.javafx.Implicits._
import io.github.bertderbecker.scalapfui.javafx.FXElementTag
import io.github.bertderbecker.scalapfui.javafx.attribute.FXAttribute
import io.github.bertderbecker.scalapfui.javafx.scene.Area
import io.github.bertderbecker.scalapfui.javafx.scene.Area.{Height, Width}

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


  implicit def readableHeightAttr2HeightAttr(
                                              readable: ReadableAttribute[Double, Height]
                                            ): Attribute[Double, JFXRectangle] =
    FXAttribute(_.heightProperty().asObject())


  implicit def readableWidthAttr2WidthAttr(
                                              readable: ReadableAttribute[Double, Width]
                                            ): Attribute[Double, JFXRectangle] =
    FXAttribute(_.widthProperty().asObject())

}
