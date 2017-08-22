package io.github.bertderbecker.scalapfui.javafx.stage

import javafx.event.EventDispatcher
import javafx.scene.{Scene, Parent => JFXParent}
import javafx.stage.{Window, WindowEvent}

import io.github.bertderbecker.scalapfui.attribute.{Attribute, ReadableAttribute}
import io.github.bertderbecker.scalapfui.javafx.FXElement
import io.github.bertderbecker.scalapfui.javafx.Implicits._
import io.github.bertderbecker.scalapfui.javafx.attribute.{FXAttribute, FXReadableAttribute}

object WindowExts {

  val eventDispatcher: Attribute[EventDispatcher, Window] =
    FXAttribute[EventDispatcher, Window](_.eventDispatcherProperty())


  val focused: ReadableAttribute[Boolean, Window] =
    FXReadableAttribute[java.lang.Boolean, Window](_.focusedProperty().asObject())

  val height: ReadableAttribute[Double, Window] =
    FXReadableAttribute[java.lang.Double, Window](_.heightProperty().asObject())

  val onCloseRequest: Attribute[WindowEvent => FXElement[_ <: JFXParent], Window] =
    FXAttribute.forEventHandler(_.onCloseRequestProperty(), _.asInstanceOf)

  val onHidden: Attribute[WindowEvent => FXElement[_ <: JFXParent], Window] =
    FXAttribute.forEventHandler(_.onHiddenProperty(), _.asInstanceOf)

  val onHiding: Attribute[WindowEvent => FXElement[_ <: JFXParent], Window] =
    FXAttribute.forEventHandler(_.onHidingProperty(), _.asInstanceOf)

  val onShowing: Attribute[WindowEvent => FXElement[_ <: JFXParent], Window] =
    FXAttribute.forEventHandler(_.onShowingProperty(), _.asInstanceOf)

  val onShown: Attribute[WindowEvent => FXElement[_ <: JFXParent], Window] =
    FXAttribute.forEventHandler(_.onShownProperty(), _.asInstanceOf)

  val opacity: Attribute[Double, Window] =
    FXAttribute[java.lang.Double, Window](_.opacityProperty().asObject())

  val scene: ReadableAttribute[Scene, Window] =
    FXReadableAttribute[Scene, Window](_.sceneProperty())

  val showing: ReadableAttribute[Boolean, Window] =
    FXReadableAttribute[java.lang.Boolean, Window](_.showingProperty().asObject())

  val width: ReadableAttribute[Double, Window] =
    FXReadableAttribute[java.lang.Double, Window](_.widthProperty().asObject())

  val x: ReadableAttribute[Double, Window] =
    FXReadableAttribute[java.lang.Double, Window](_.xProperty().asObject())

  val y: ReadableAttribute[Double, Window] =
    FXReadableAttribute[java.lang.Double, Window](_.yProperty().asObject())
}
