package io.github.bertderbecker.scalapfui.javafx.stage

import javafx.scene.{Scene, Parent => JFXParent}
import javafx.stage.{Window, WindowEvent}

import io.github.bertderbecker.scalapfui.attribute.{Attribute, ReadableAttribute}
import io.github.bertderbecker.scalapfui.javafx.FXElement
import io.github.bertderbecker.scalapfui.javafx.Implicits._
import io.github.bertderbecker.scalapfui.javafx.attribute.{FXAttribute, FXReadableAttribute}
import io.github.bertderbecker.scalapfui.javafx.property.FXProperty

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


  trait SharedAttributes {

    val onCloseRequest: Attribute[WindowEvent => FXElement[_ <: JFXParent], Window] =
      FXAttribute.forEventHandlerUnwrapped(n => FXProperty(n.onCloseRequestProperty()))

    val onHidden: Attribute[WindowEvent => FXElement[_ <: JFXParent], Window] =
      FXAttribute.forEventHandlerUnwrapped(n => FXProperty(n.onHiddenProperty()))

    val onHiding: Attribute[WindowEvent => FXElement[_ <: JFXParent], Window] =
      FXAttribute.forEventHandlerUnwrapped(n => FXProperty(n.onHidingProperty()))

    val onShowing: Attribute[WindowEvent => FXElement[_ <: JFXParent], Window] =
      FXAttribute.forEventHandlerUnwrapped(n => FXProperty(n.onShowingProperty()))

    val onShown: Attribute[WindowEvent => FXElement[_ <: JFXParent], Window] =
      FXAttribute.forEventHandlerUnwrapped(n => FXProperty(n.onShownProperty()))
  }

  object window extends SharedAttributes
}
