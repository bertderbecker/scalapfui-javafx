package io.github.bertderbecker.scalapfui.javafx.geometry

import javafx.geometry.{Insets => JFXInsets}

import scala.language.implicitConversions

case class Insets(top: Double, right: Double, bottom: Double, left: Double)

object Insets {

  trait InsetInstances {

    implicit def jFXInsets2Insets(jFXInsets: JFXInsets): Insets =
      Insets(jFXInsets.getTop, jFXInsets.getRight, jFXInsets.getBottom, jFXInsets.getLeft)


    implicit def insets2JFXInsets(insets: Insets): JFXInsets =
      new JFXInsets(insets.top, insets.right, insets.bottom, insets.left)

  }

  def forAll(all: Double) = Insets(all, all, all, all)
}
