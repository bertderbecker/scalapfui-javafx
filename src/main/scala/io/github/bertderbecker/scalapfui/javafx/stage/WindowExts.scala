package io.github.bertderbecker.scalapfui.javafx.stage

import javafx.scene.{Scene, Parent => JFXParent}
import javafx.stage.Window

import io.github.bertderbecker.scalapfui.attribute.{Attribute, ReadableAttribute}
import io.github.bertderbecker.scalapfui.javafx.Implicits._
import io.github.bertderbecker.scalapfui.javafx.attribute.{FXAttribute, FXReadableAttribute}

//Ready
object WindowExts {

  val focused: ReadableAttribute[Boolean, Window] =
    FXReadableAttribute[java.lang.Boolean, Window](_.focusedProperty().asObject())

  val opacity: Attribute[Double, Window] =
    FXAttribute[java.lang.Double, Window](_.opacityProperty().asObject())

  val scene: ReadableAttribute[Scene, Window] =
    FXReadableAttribute[Scene, Window](_.sceneProperty())

  val showing: ReadableAttribute[Boolean, Window] =
    FXReadableAttribute[java.lang.Boolean, Window](_.showingProperty().asObject())

  val x: ReadableAttribute[Double, Window] =
    FXReadableAttribute[java.lang.Double, Window](_.xProperty().asObject())

  val y: ReadableAttribute[Double, Window] =
    FXReadableAttribute[java.lang.Double, Window](_.yProperty().asObject())

}
