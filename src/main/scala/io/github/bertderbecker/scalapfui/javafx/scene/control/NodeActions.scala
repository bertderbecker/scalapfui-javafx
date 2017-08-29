package io.github.bertderbecker.scalapfui.javafx.scene.control

import javafx.event.{Event, EventHandler}
import javafx.scene.Node
import javafx.scene.input.ContextMenuEvent
import javafx.scene.{Node, Parent => JFXParent}

import io.github.bertderbecker.scalapfui.Modifier
import javafx.beans.property.{SimpleObjectProperty, Property => JFXProperty}

import io.github.bertderbecker.scalapfui.attribute.Attribute
import io.github.bertderbecker.scalapfui.javafx.FXElement
import io.github.bertderbecker.scalapfui.javafx.attribute.FXAttribute

import scala.language.implicitConversions

object NodeActions {


  lazy val onContextMenuRequested
  : Attribute[ContextMenuEvent => FXElement[_ <: JFXParent], Node] =
    FXAttribute.forEventHandler[ContextMenuEvent, Node](_.onContextMenuRequestedProperty())


}
