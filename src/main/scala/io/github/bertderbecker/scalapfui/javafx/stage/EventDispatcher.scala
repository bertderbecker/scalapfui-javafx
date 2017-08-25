package io.github.bertderbecker.scalapfui.javafx.stage

import javafx.beans.property.{Property => JFXProperty}
import javafx.event.{EventDispatcher => JFXEventDispatcher}
import javafx.scene.Node
import javafx.stage.Window

import io.github.bertderbecker.scalapfui.Modifier
import io.github.bertderbecker.scalapfui.attribute.Attribute
import io.github.bertderbecker.scalapfui.javafx.attribute.FXAttribute

import scala.language.implicitConversions

case class EventDispatcher(property: JFXProperty[JFXEventDispatcher])

object EventDispatcher {

  val eventDispatcher: Attribute[JFXEventDispatcher, EventDispatcher] =
    FXAttribute[JFXEventDispatcher, EventDispatcher](_.property)

  implicit def eventDispatcher2Window[T <: Window](
                                                    mod: Modifier[JFXEventDispatcher, EventDispatcher]
                                                  ): Modifier[JFXEventDispatcher, T] =
    mod.mapApply(native => EventDispatcher(native.eventDispatcherProperty()))


  implicit def eventDispatcher2Node[T <: Node](
                                                mod: Modifier[JFXEventDispatcher, EventDispatcher]
                                              ): Modifier[JFXEventDispatcher, T] =
    mod.mapApply(native => EventDispatcher(native.eventDispatcherProperty()))

}