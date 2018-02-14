package io.github.bertderbecker.scalapfui.javafx.scene.layout

import javafx.geometry.Pos
import javafx.scene.layout.{HBox => JFXHBox, VBox => JFXVBox}

import io.github.bertderbecker.scalapfui.Modifier
import io.github.bertderbecker.scalapfui.attribute.Attribute
import io.github.bertderbecker.scalapfui.javafx.FXParentTag
import io.github.bertderbecker.scalapfui.javafx.Implicits._
import io.github.bertderbecker.scalapfui.javafx.attribute.FXAttribute

import scala.language.implicitConversions

object OrderedBoxes {

  val VBox: FXParentTag[JFXVBox] = FXParentTag(() => new JFXVBox(), _.getChildren)
  val HBox: FXParentTag[JFXHBox] = FXParentTag(() => new JFXHBox(), _.getChildren)

  trait VBoxAttributes extends PaneExts.Attributes {

    val alignment: Attribute[Pos, JFXVBox] = FXAttribute.apply[Pos, JFXVBox](_.alignmentProperty())

    object Alignment {
      val BaselineCenter: Modifier[Pos, JFXVBox] = alignment := Pos.BASELINE_CENTER
      val BaselineLeft: Modifier[Pos, JFXVBox] = alignment := Pos.BASELINE_LEFT
      val BaselineRight: Modifier[Pos, JFXVBox] = alignment := Pos.BASELINE_RIGHT
      val BottomCenter: Modifier[Pos, JFXVBox] = alignment := Pos.BOTTOM_CENTER
      val BottomLeft: Modifier[Pos, JFXVBox] = alignment := Pos.BOTTOM_LEFT
      val BottomRight: Modifier[Pos, JFXVBox] = alignment := Pos.BOTTOM_RIGHT
      val Center: Modifier[Pos, JFXVBox] = alignment := Pos.CENTER
      val CenterLeft: Modifier[Pos, JFXVBox] = alignment := Pos.CENTER_LEFT
      val CenterRight: Modifier[Pos, JFXVBox] = alignment := Pos.CENTER_RIGHT
      val TopCenter: Modifier[Pos, JFXVBox] = alignment := Pos.TOP_CENTER
      val TopLeft: Modifier[Pos, JFXVBox] = alignment := Pos.TOP_LEFT
      val TopRight: Modifier[Pos, JFXVBox] = alignment := Pos.TOP_RIGHT
    }

    val fillWidth: Attribute[Boolean, JFXVBox] =
      FXAttribute[java.lang.Boolean, JFXVBox](_.fillWidthProperty().asObject())


    val spacing: Attribute[Double, JFXVBox] =
      FXAttribute[java.lang.Double, JFXVBox](_.spacingProperty().asObject())

  }

  trait HBoxAttributes extends PaneExts.Attributes {

    val alignment: Attribute[Pos, JFXHBox] = FXAttribute.apply[Pos, JFXHBox](_.alignmentProperty())

    object Alignment {
      val BaselineCenter: Modifier[Pos, JFXHBox] = alignment := Pos.BASELINE_CENTER
      val BaselineLeft: Modifier[Pos, JFXHBox] = alignment := Pos.BASELINE_LEFT
      val BaselineRight: Modifier[Pos, JFXHBox] = alignment := Pos.BASELINE_RIGHT
      val BottomCenter: Modifier[Pos, JFXHBox] = alignment := Pos.BOTTOM_CENTER
      val BottomLeft: Modifier[Pos, JFXHBox] = alignment := Pos.BOTTOM_LEFT
      val BottomRight: Modifier[Pos, JFXHBox] = alignment := Pos.BOTTOM_RIGHT
      val Center: Modifier[Pos, JFXHBox] = alignment := Pos.CENTER
      val CenterLeft: Modifier[Pos, JFXHBox] = alignment := Pos.CENTER_LEFT
      val CenterRight: Modifier[Pos, JFXHBox] = alignment := Pos.CENTER_RIGHT
      val TopCenter: Modifier[Pos, JFXHBox] = alignment := Pos.TOP_CENTER
      val TopLeft: Modifier[Pos, JFXHBox] = alignment := Pos.TOP_LEFT
      val TopRight: Modifier[Pos, JFXHBox] = alignment := Pos.TOP_RIGHT
    }

    val fillHeight: Attribute[Boolean, JFXHBox] =
      FXAttribute[java.lang.Boolean, JFXHBox](_.fillHeightProperty().asObject())

    val spacing: Attribute[Double, JFXHBox] =
      FXAttribute[java.lang.Double, JFXHBox](_.spacingProperty().asObject())

  }


  object vBox extends VBoxAttributes

  object hBox extends HBoxAttributes

}
