package io.github.bertderbecker.scalapfui.javafx.stage

import javafx.beans.property.{DoubleProperty, Property => JFXProperty}
import javafx.scene.Node
import javafx.stage.Window

import io.github.bertderbecker.scalapfui.Modifier
import io.github.bertderbecker.scalapfui.attribute.Attribute
import io.github.bertderbecker.scalapfui.javafx.Implicits._
import io.github.bertderbecker.scalapfui.javafx.attribute.FXAttribute

import scala.language.implicitConversions

case class Opacity(property: DoubleProperty)

object Opacity {

  val opacity: Attribute[Double, Opacity] =
    FXAttribute[java.lang.Double, Opacity](_.property.asObject())

  implicit def Opacity2Window[T <: Window](
                                            mod: Modifier[Double, Opacity]
                                          ): Modifier[Double, T] =
    mod.mapApply(native => Opacity(native.opacityProperty()))


  implicit def Opacity2Node[T <: Node](
                                        mod: Modifier[Double, Opacity]
                                      ): Modifier[Double, T] =
    mod.mapApply(native => Opacity(native.opacityProperty()))

}