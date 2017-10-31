package io.github.bertderbecker.scalapfui.javafx.scene.control

import javafx.event.{ActionEvent, Event, EventHandler}
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

import scala.language.implicitConversions

//Ready
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


  //onAction is ready

  val onMenuValidation: Attribute[Event => FXElement[_ <: JFXParent], JFXMenuItem] =
    FXAttribute.forEventHandlerUnwrapped(x => FXProperty(x.onMenuValidationProperty()))


  val parentMenu: ReadableAttribute[Menu, JFXMenuItem] =
    FXReadableAttribute[Menu, JFXMenuItem](_.parentMenuProperty())

  val parentPopup: ReadableAttribute[ContextMenu, JFXMenuItem] =
    FXReadableAttribute[ContextMenu, JFXMenuItem](_.parentPopupProperty())

  //Style is ready

  trait SharedAttributes {

    val text: Attribute[String, JFXMenuItem] = FXAttribute[String, JFXMenuItem](_.textProperty())


    lazy val onAction: Attribute[(ActionEvent) => FXElement[_ <: JFXParent], JFXMenuItem] =
      FXAttribute.forEventHandlerUnwrapped(x => FXProperty(x.onActionProperty()))


    val style: Attribute[String, JFXMenuItem] = FXAttribute[String, JFXMenuItem](_.styleProperty())
  }

  object menuItem extends SharedAttributes

  val visible: Attribute[Boolean, JFXMenuItem] =
    FXAttribute[java.lang.Boolean, JFXMenuItem](_.visibleProperty())
}
