package io.github.bertderbecker.scalapfui.javafx.scene.control

import javafx.beans.property.ObjectProperty
import javafx.event.{ActionEvent, Event, EventHandler}
import javafx.scene.control.{MenuItem, TextField}
import javafx.scene.input._
import javafx.scene.{Node, Parent => JFXParent}

import io.github.bertderbecker.scalapfui.Modifier
import io.github.bertderbecker.scalapfui.attribute.Attribute
import io.github.bertderbecker.scalapfui.javafx.Implicits._
import io.github.bertderbecker.scalapfui.javafx.FXElement
import io.github.bertderbecker.scalapfui.javafx.attribute.FXAttribute
import io.github.bertderbecker.scalapfui.javafx.property.FXProperty
import io.github.bertderbecker.scalapfui.property.Property

import scala.language.implicitConversions

object Actions {

  case class OnAction(property: Property[EventHandler[ActionEvent]])

  lazy val onAction: Attribute[ActionEvent => FXElement[_ <: JFXParent], OnAction] =
    FXAttribute.forEventHandlerUnwrapped(_.property)

  implicit def onAction2TextFieldMod(
                                      mod: Modifier[ActionEvent => FXElement[_ <: JFXParent], OnAction]
                                    ): Modifier[(ActionEvent) => FXElement[_ <: JFXParent], TextField] =
    mod.mapApply(
      native => OnAction(
        FXProperty(native.onActionProperty())
      )
    )

  implicit def onAction2MenuItemMod(
                                     mod: Modifier[ActionEvent => FXElement[_ <: JFXParent], OnAction]
                                   ): Modifier[(ActionEvent) => FXElement[_ <: JFXParent], MenuItem] =
    mod.mapApply(//TODO: Make window in other actions lazy
      native => OnAction(FXProperty(native.onActionProperty()))
    )

}
