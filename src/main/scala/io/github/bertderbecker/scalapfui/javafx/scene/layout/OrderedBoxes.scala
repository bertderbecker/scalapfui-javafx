package io.github.bertderbecker.scalapfui.javafx.scene.layout

import javafx.beans.property.{BooleanProperty, DoubleProperty}

import io.github.bertderbecker.scalapfui.attribute.Attribute
import javafx.scene.layout.{VBox => JFXVBox}
import javafx.scene.layout.{HBox => JFXHBox}

import io.github.bertderbecker.scalapfui.Modifier
import io.github.bertderbecker.scalapfui.javafx.Implicits._
import io.github.bertderbecker.scalapfui.javafx.FXParentTag
import io.github.bertderbecker.scalapfui.javafx.attribute.FXAttribute
import io.github.bertderbecker.scalapfui.javafx.scene.control.Alignment

import scala.language.implicitConversions

object OrderedBoxes {

  val VBox: FXParentTag[JFXVBox] = FXParentTag(() => new JFXVBox(), _.getChildren)
  val HBox: FXParentTag[JFXHBox] = FXParentTag(() => new JFXHBox(), _.getChildren)


  val fillWidth: Attribute[Boolean, JFXVBox] =
    FXAttribute[java.lang.Boolean, JFXVBox](_.fillWidthProperty().asObject())

  val fillHeight: Attribute[Boolean, JFXHBox] =
    FXAttribute[java.lang.Boolean, JFXHBox](_.fillHeightProperty().asObject())

  case class Spacing(property: DoubleProperty)

  val spacing: Attribute[Double, Spacing] =
    FXAttribute[java.lang.Double, Spacing](_.property.asObject())


  implicit def spacing2HBox[T <: JFXHBox](mod: Modifier[Double, Spacing]): Modifier[Double, T] =
    mod.mapApply[JFXHBox](native => Spacing(native.spacingProperty()))

  implicit def spacing2VBox[T <: JFXVBox](mod: Modifier[Double, Spacing]): Modifier[Double, T] =
    mod.mapApply[JFXVBox](native => Spacing(native.spacingProperty()))

}
