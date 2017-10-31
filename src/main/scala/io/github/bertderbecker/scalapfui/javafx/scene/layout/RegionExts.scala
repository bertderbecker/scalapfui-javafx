package io.github.bertderbecker.scalapfui.javafx.scene.layout

import io.github.bertderbecker.scalapfui.javafx.{FXElementTag, FXParent, FXParentTag}
import javafx.scene.layout.{BackgroundFill, Border, CornerRadii, Background => JFXBackground, Region => JFXRegion}
import javafx.scene.paint.Paint
import javafx.scene.shape.Shape
import javafx.geometry.{Insets => JFXInsets}

import io.github.bertderbecker.scalapfui.attribute.{Attribute, ReadableAttribute}
import io.github.bertderbecker.scalapfui.javafx.Implicits._
import io.github.bertderbecker.scalapfui.javafx.attribute.{FXAttribute, FXReadableAttribute}
import io.github.bertderbecker.scalapfui.javafx.geometry.Insets

object RegionExts {

  val Region: FXElementTag[JFXRegion] = FXElementTag(() => new JFXRegion())

  val background: Attribute[JFXBackground, JFXRegion] =
    FXAttribute[JFXBackground, JFXRegion](_.backgroundProperty())

  object Background {
    def fillWith(paint: Paint) =
      new JFXBackground(new BackgroundFill(paint, CornerRadii.EMPTY, JFXInsets.EMPTY))
  }

  trait SharedAttributes {

    val height: ReadableAttribute[Double, JFXRegion] =
      FXReadableAttribute[java.lang.Double, JFXRegion](_.heightProperty().asObject())

    val width: ReadableAttribute[Double, JFXRegion] =
      FXReadableAttribute[java.lang.Double, JFXRegion](_.widthProperty().asObject())


    val maxHeight: ReadableAttribute[Double, JFXRegion] =
      FXReadableAttribute[java.lang.Double, JFXRegion](_.maxHeightProperty().asObject())


    val maxWidth: ReadableAttribute[Double, JFXRegion] =
      FXReadableAttribute[java.lang.Double, JFXRegion](_.maxWidthProperty().asObject())


    val prefHeight: ReadableAttribute[Double, JFXRegion] =
      FXReadableAttribute[java.lang.Double, JFXRegion](_.prefHeightProperty().asObject())

    val prefWidth: ReadableAttribute[Double, JFXRegion] =
      FXReadableAttribute[java.lang.Double, JFXRegion](_.prefWidthProperty().asObject())

  }

  object region extends SharedAttributes

  val border: Attribute[Border, JFXRegion] =
    FXAttribute[Border, JFXRegion](_.borderProperty())

  val cacheShape: Attribute[Boolean, JFXRegion] =
    FXAttribute[java.lang.Boolean, JFXRegion](_.cacheShapeProperty())

  val centerShape: Attribute[Boolean, JFXRegion] =
    FXAttribute[java.lang.Boolean, JFXRegion](_.centerShapeProperty())

  val insets: ReadableAttribute[Insets, JFXRegion] =
    FXReadableAttribute[javafx.geometry.Insets, JFXRegion](_.insetsProperty())

  val opaqueInsets: Attribute[Insets, JFXRegion] =
    FXAttribute[javafx.geometry.Insets, JFXRegion](_.opaqueInsetsProperty())

  val padding: Attribute[Insets, JFXRegion] =
    FXAttribute[javafx.geometry.Insets, JFXRegion](_.paddingProperty())

  val scaleShape: Attribute[Boolean, JFXRegion] =
    FXAttribute[java.lang.Boolean, JFXRegion](_.scaleShapeProperty())

  val shape: Attribute[Shape, JFXRegion] =
    FXAttribute[Shape, JFXRegion](_.shapeProperty())

  val snapToPixel: Attribute[Boolean, JFXRegion] =
    FXAttribute[java.lang.Boolean, JFXRegion](_.snapToPixelProperty())

}
