package io.github.bertderbecker.scalapfui.javafx

import javafx.application.Platform

import scala.language.{higherKinds, implicitConversions}

object UI {

  def apply(u: => Unit): Unit = Platform.runLater(() => u)

}
