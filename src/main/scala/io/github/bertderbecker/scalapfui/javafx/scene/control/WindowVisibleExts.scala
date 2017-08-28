package io.github.bertderbecker.scalapfui.javafx.scene.control

import javafx.stage.{Window, WindowEvent}

import javafx.scene.{Scene, Parent => JFXParent}
import io.github.bertderbecker.scalapfui.attribute.Attribute
import io.github.bertderbecker.scalapfui.javafx.FXElement
import io.github.bertderbecker.scalapfui.javafx.attribute.FXAttribute
import io.github.bertderbecker.scalapfui.javafx.property.FXProperty

object WindowVisibleExts {

  val onCloseRequest: Attribute[WindowEvent => FXElement[_ <: JFXParent], Window] =
    FXAttribute.forEventHandler(n => FXProperty(n.onCloseRequestProperty()), _.asInstanceOf)

  val onHidden: Attribute[WindowEvent => FXElement[_ <: JFXParent], Window] =
    FXAttribute.forEventHandler(n => FXProperty(n.onHiddenProperty()), _.asInstanceOf)

  val onHiding: Attribute[WindowEvent => FXElement[_ <: JFXParent], Window] =
    FXAttribute.forEventHandler(n => FXProperty(n.onHidingProperty()), _.asInstanceOf)

  val onShowing: Attribute[WindowEvent => FXElement[_ <: JFXParent], Window] =
    FXAttribute.forEventHandler(n => FXProperty(n.onShowingProperty()), _.asInstanceOf)

  val onShown: Attribute[WindowEvent => FXElement[_ <: JFXParent], Window] =
    FXAttribute.forEventHandler(n => FXProperty(n.onShownProperty()), _.asInstanceOf)
}
