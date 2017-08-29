package io.github.bertderbecker.scalapfui.javafx.scene.control

import javafx.stage.{Window, WindowEvent}

import javafx.scene.{Scene, Parent => JFXParent}
import io.github.bertderbecker.scalapfui.attribute.Attribute
import io.github.bertderbecker.scalapfui.javafx.FXElement
import io.github.bertderbecker.scalapfui.javafx.attribute.FXAttribute
import io.github.bertderbecker.scalapfui.javafx.property.FXProperty

object WindowVisibleExts {

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
