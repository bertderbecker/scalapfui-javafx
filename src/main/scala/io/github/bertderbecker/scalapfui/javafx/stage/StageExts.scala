package io.github.bertderbecker.scalapfui.javafx.stage

import javafx.beans.property.adapter.JavaBeanObjectPropertyBuilder
import javafx.beans.property.{Property => JFXProperty}
import javafx.scene.Scene
import javafx.scene.input.KeyCombination
import javafx.stage.Stage

import io.github.bertderbecker.scalapfui.attribute.{Attribute, ReadableAttribute}
import io.github.bertderbecker.scalapfui.javafx.Implicits._
import io.github.bertderbecker.scalapfui.javafx.attribute.{FXAttribute, FXReadableAttribute}
import io.github.bertderbecker.scalapfui.javafx.property.FXProperty
import io.github.bertderbecker.scalapfui.javafx.{FXElement, FXElementTag}

//Ready
object StageExts {

  val Stage = FXElementTag(() => new Stage())


  val scene: Attribute[FXElement[Scene], Stage] =
    FXAttribute.fromProperty[FXElement[Scene], Stage] { native =>
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
  
  
  object stage {

    val height: ReadableAttribute[Double, Stage] =
      FXReadableAttribute[java.lang.Double, Stage](_.heightProperty().asObject())

    val width: ReadableAttribute[Double, Stage] =
      FXReadableAttribute[java.lang.Double, Stage](_.widthProperty().asObject())


    val maxHeight: ReadableAttribute[Double, Stage] =
      FXReadableAttribute[java.lang.Double, Stage](_.maxHeightProperty().asObject())


    val maxWidth: ReadableAttribute[Double, Stage] =
      FXReadableAttribute[java.lang.Double, Stage](_.maxWidthProperty().asObject())

  }
  
  val alwaysOnTop: ReadableAttribute[Boolean, Stage] =
    FXReadableAttribute[java.lang.Boolean, Stage](_.alwaysOnTopProperty().asObject())

  val fullScreenExitHint: Attribute[String, Stage] =
    FXAttribute[String, Stage](_.fullScreenExitHintProperty())

  //TODO: Better Support for KeyCombination
  val fullScreenExitKey: Attribute[KeyCombination, Stage] =
    FXAttribute[KeyCombination, Stage](_.fullScreenExitKeyProperty())

  val fullScreen: ReadableAttribute[Boolean, Stage] =
    FXReadableAttribute[java.lang.Boolean, Stage](_.fullScreenProperty().asObject())

  val iconified: ReadableAttribute[Boolean, Stage] =
    FXReadableAttribute[java.lang.Boolean, Stage](_.iconifiedProperty().asObject())

  val maxHeight: Attribute[Double, Stage] =
    FXAttribute[java.lang.Double, Stage](_.maxHeightProperty().asObject())

  val maximized: ReadableAttribute[Boolean, Stage] =
    FXReadableAttribute[java.lang.Boolean, Stage](_.maximizedProperty().asObject())

  val maxWidth: Attribute[Double, Stage] =
    FXAttribute[java.lang.Double, Stage](_.maxWidthProperty().asObject())

  val minHeight: Attribute[Double, Stage] =
    FXAttribute[java.lang.Double, Stage](_.minHeightProperty().asObject())

  val minWidth: Attribute[Double, Stage] =
    FXAttribute[java.lang.Double, Stage](_.minWidthProperty().asObject())

  val resizable: Attribute[Boolean, Stage] =
    FXAttribute[java.lang.Boolean, Stage](_.resizableProperty().asObject())

  val title: Attribute[String, Stage] =
    FXAttribute[String, Stage](_.titleProperty())


}
