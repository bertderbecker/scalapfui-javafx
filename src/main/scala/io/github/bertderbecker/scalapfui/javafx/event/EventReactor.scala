package io.github.bertderbecker.scalapfui.javafx.event

import javafx.scene.Scene

import javafx.scene.{Scene, Parent => JFXParent}
import io.github.bertderbecker.scalapfui.SimpleModifier
import io.github.bertderbecker.scalapfui.javafx.{FXElement, JFXApp}

case class EventReactor[E](op: E => Unit)

object EventReactor {

  def modify[E](mods: SimpleModifier*): EventReactor[E] = EventReactor[E](_ => mods.foreach(_.apply()))

  def rebuild[E](rootParent: E => FXElement[_ <: JFXParent]): EventReactor[E] = EventReactor[E] { event =>
    JFXApp.Stage.setScene(new Scene(rootParent(event).render))
  }

}