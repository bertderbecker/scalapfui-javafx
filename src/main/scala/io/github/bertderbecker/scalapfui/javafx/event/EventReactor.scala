package io.github.bertderbecker.scalapfui.javafx.event

import javafx.scene.{Scene, Parent => JFXParent}

import io.github.bertderbecker.scalapfui.RunLater
import io.github.bertderbecker.scalapfui.javafx.{FXElement, JFXApp}

case class EventReactor[E](op: E => Unit)

object EventReactor {

  def modify[E](mods: RunLater*): EventReactor[E] = EventReactor[E](_ => mods.foreach(_.run()))

  def rebuild[E](rootParent: E => FXElement[_ <: JFXParent]): EventReactor[E] = EventReactor[E] { event =>
    JFXApp.Stage.setScene(new Scene(rootParent(event).render))
  }

}