package io.github.bertderbecker.scalapfui.javafx.stage

import javafx.beans.property.adapter.JavaBeanObjectPropertyBuilder
import javafx.beans.property.{Property => JFXProperty}
import javafx.scene.Scene
import javafx.scene.input.KeyCombination
import javafx.stage.{Stage => JFXStage}

import io.github.bertderbecker.scalapfui.attribute.{Attribute, ReadableAttribute}
import io.github.bertderbecker.scalapfui.javafx.Implicits._
import io.github.bertderbecker.scalapfui.javafx.attribute.{FXAttribute, FXReadableAttribute, FXSimpleWritableAttribute}
import io.github.bertderbecker.scalapfui.javafx.property.FXProperty
import io.github.bertderbecker.scalapfui.javafx.{FXElement, FXElementTag}

//Ready
object StageExts {

  val Stage = FXElementTag(() => new JFXStage())

  trait Attributes extends WindowExts.Attributes {

    val alwaysOnTop: ReadableAttribute[Boolean, JFXStage] =
      FXReadableAttribute[java.lang.Boolean, JFXStage](_.alwaysOnTopProperty().asObject())

    val fullScreenExitHint: Attribute[String, JFXStage] =
      FXAttribute[String, JFXStage](_.fullScreenExitHintProperty())

    //TODO: Better Support for KeyCombination
    val fullScreenExitKey: Attribute[KeyCombination, JFXStage] =
      FXAttribute[KeyCombination, JFXStage](_.fullScreenExitKeyProperty())

    val fullScreen: ReadableAttribute[Boolean, JFXStage] =
      FXReadableAttribute[java.lang.Boolean, JFXStage](_.fullScreenProperty().asObject())

    val height: ReadableAttribute[Double, JFXStage] =
      FXReadableAttribute[java.lang.Double, JFXStage](_.heightProperty().asObject())

    val iconified: ReadableAttribute[Boolean, JFXStage] =
      FXReadableAttribute[java.lang.Boolean, JFXStage](_.iconifiedProperty().asObject())

    val initHeight: FXSimpleWritableAttribute[Double, JFXStage] =
      FXSimpleWritableAttribute[Double, JFXStage](x => stage => stage.setHeight(x))

    val initWidth: FXSimpleWritableAttribute[Double, JFXStage] =
      FXSimpleWritableAttribute[Double, JFXStage](x => stage => stage.setWidth(x))

    val maxHeight: Attribute[Double, JFXStage] =
      FXAttribute[java.lang.Double, JFXStage](_.maxHeightProperty().asObject())

    val maximized: ReadableAttribute[Boolean, JFXStage] =
      FXReadableAttribute[java.lang.Boolean, JFXStage](_.maximizedProperty().asObject())

    val maxWidth: Attribute[Double, JFXStage] =
      FXAttribute[java.lang.Double, JFXStage](_.maxWidthProperty().asObject())

    val minHeight: Attribute[Double, JFXStage] =
      FXAttribute[java.lang.Double, JFXStage](_.minHeightProperty().asObject())

    val minWidth: Attribute[Double, JFXStage] =
      FXAttribute[java.lang.Double, JFXStage](_.minWidthProperty().asObject())

    val resizable: Attribute[Boolean, JFXStage] =
      FXAttribute[java.lang.Boolean, JFXStage](_.resizableProperty().asObject())

    val scene: Attribute[FXElement[Scene], JFXStage] =
      FXAttribute.fromProperty[FXElement[Scene], JFXStage] { native =>
        propertyInvariant.imap(
          FXProperty(
            JavaBeanObjectPropertyBuilder
              .create()
              .bean(native)
              .name("Scene")
              .build()
              .asInstanceOf[JFXProperty[Scene]]
          )
        )(p => FXElement.wrap(p))(_.render)
      }

    val title: Attribute[String, JFXStage] =
      FXAttribute[String, JFXStage](_.titleProperty())

    val width: ReadableAttribute[Double, JFXStage] =
      FXReadableAttribute[java.lang.Double, JFXStage](_.widthProperty().asObject())

  }

  object stage extends Attributes


}
