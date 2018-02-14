package io.github.bertderbecker.scalapfui.javafx.scene.control

import javafx.event.{ActionEvent, Event}
import javafx.scene.control.{ContextMenu, Menu, MenuItem => JFXMenuItem}
import javafx.scene.input.KeyCombination
import javafx.scene.{Node, Parent => JFXParent}

import io.github.bertderbecker.scalapfui.attribute.{Attribute, ReadableAttribute}
import io.github.bertderbecker.scalapfui.javafx.FXElementTag
import io.github.bertderbecker.scalapfui.javafx.Implicits._
import io.github.bertderbecker.scalapfui.javafx.attribute.{FXAttribute, FXReadableAttribute}
import io.github.bertderbecker.scalapfui.javafx.event.EventReactor

import scala.language.implicitConversions

//Ready
object MenuItemExts {


  val MenuItem: FXElementTag[JFXMenuItem] = FXElementTag(() => new JFXMenuItem())

  trait Attributes {

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

    lazy val onAction: Attribute[EventReactor[ActionEvent], JFXMenuItem] =
      FXAttribute.forEventReactor(_.onActionProperty())

    val onMenuValidation: Attribute[EventReactor[Event], JFXMenuItem] =
      FXAttribute.forEventReactor(_.onMenuValidationProperty())

    val parentMenu: ReadableAttribute[Menu, JFXMenuItem] =
      FXReadableAttribute[Menu, JFXMenuItem](_.parentMenuProperty())

    val parentPopup: ReadableAttribute[ContextMenu, JFXMenuItem] =
      FXReadableAttribute[ContextMenu, JFXMenuItem](_.parentPopupProperty())

    val style: Attribute[String, JFXMenuItem] = FXAttribute[String, JFXMenuItem](_.styleProperty())

    val text: Attribute[String, JFXMenuItem] = FXAttribute[String, JFXMenuItem](_.textProperty())

  }

  object menuItem extends Attributes

  val visible: Attribute[Boolean, JFXMenuItem] =
    FXAttribute[java.lang.Boolean, JFXMenuItem](_.visibleProperty())
}
