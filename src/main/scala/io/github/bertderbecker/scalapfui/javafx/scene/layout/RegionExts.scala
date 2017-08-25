package io.github.bertderbecker.scalapfui.javafx.scene.layout

import io.github.bertderbecker.scalapfui.javafx.{FXElementTag, FXParent, FXParentTag}
import javafx.scene.layout.{Background, Border, Region => JFXRegion}
import javafx.scene.shape.Shape

import io.github.bertderbecker.scalapfui.attribute.{Attribute, ReadableAttribute}
import io.github.bertderbecker.scalapfui.javafx.Implicits._
import io.github.bertderbecker.scalapfui.javafx.attribute.{FXAttribute, FXReadableAttribute}
import io.github.bertderbecker.scalapfui.javafx.geometry.Insets

object RegionExts {

  val Region: FXElementTag[JFXRegion] = FXElementTag(() => new JFXRegion())

  val background: Attribute[Background, JFXRegion] =
    FXAttribute[Background, JFXRegion](_.backgroundProperty())

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
