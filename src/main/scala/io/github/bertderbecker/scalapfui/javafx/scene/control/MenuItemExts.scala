package io.github.bertderbecker.scalapfui.javafx.scene.control

import javafx.event.{Event, EventHandler}
import javafx.scene.Node
import javafx.scene.control.{ContextMenu, Menu, MenuItem => JFXMenuItem}
import javafx.scene.input.KeyCombination
import javafx.scene.{Node, Parent => JFXParent}
import javafx.stage.Window

import io.github.bertderbecker.scalapfui.Modifier
import io.github.bertderbecker.scalapfui.attribute.{Attribute, ReadableAttribute}
import io.github.bertderbecker.scalapfui.javafx.{FXElement, FXElementTag}
import io.github.bertderbecker.scalapfui.javafx.Implicits._
import io.github.bertderbecker.scalapfui.javafx.attribute.{FXAttribute, FXReadableAttribute}
import io.github.bertderbecker.scalapfui.javafx.property.FXProperty
import io.github.bertderbecker.scalapfui.property.Property

object MenuItemExts {


  val MenuItem: FXElementTag[JFXMenuItem] = FXElementTag(() => new JFXMenuItem())

  val accelerator: Attribute[KeyCombination, JFXMenuItem] =
    FXAttribute[KeyCombination, JFXMenuItem](_.acceleratorProperty())

  val disable: Attribute[Boolean, JFXMenuItem] =
    FXAttribute[java.lang.Boolean, JFXMenuItem](_.disableProperty())

  val graphic: Attribute[Node, JFXMenuItem] =
    FXAttribute[Node, JFXMenuItem](_.graphicProperty())

  val id: Attribute[String, JFXMenuItem] =
    FXAttribute[String, JFXMenuItem](_.idProperty())

  val mnemonicParsing: Attribute[Boolean, JFXMenuItem] =
    FXAttribute[java.lang.Boolean, JFXMenuItem](_.mnemonicParsingProperty())


  case class OnMenuValidation(
                               property: Property[EventHandler[Event]],
                               window: Window
                             )

  val onMenuValidation: Attribute[Event => FXElement[_ <: JFXParent], OnMenuValidation] =
    FXAttribute.forEventHandler(_.property, _.window)

  implicit def onMenuValidation2MenuMod(
                                         mod: Modifier[
                                           Event => FXElement[_ <: JFXParent],
                                           OnMenuValidation
                                           ])
  : Modifier[Event => FXElement[_ <: JFXParent], Menu] =

    mod.mapApply(
      (native: Menu) =>
        OnMenuValidation(
          FXProperty(native.onMenuValidationProperty()),
          native.getParentPopup.getScene.getWindow
        )
    )


  val parentMenu: ReadableAttribute[Menu, JFXMenuItem] =
    FXReadableAttribute[Menu, JFXMenuItem](_.parentMenuProperty())

  val parentPopup: ReadableAttribute[ContextMenu, JFXMenuItem] =
    FXReadableAttribute[ContextMenu, JFXMenuItem](_.parentPopupProperty())

  //TODO: add style from node

  val visible: Attribute[Boolean, JFXMenuItem] =
    FXAttribute[java.lang.Boolean, JFXMenuItem](_.visibleProperty())
}
