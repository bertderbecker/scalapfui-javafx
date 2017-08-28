package io.github.bertderbecker.scalapfui.javafx.scene.control

import javafx.beans.property.{Property => JFXProperty}
import javafx.scene.control.{Labeled, MenuItem, TextInputControl}

import io.github.bertderbecker.scalapfui.Modifier
import io.github.bertderbecker.scalapfui.attribute.Attribute
import io.github.bertderbecker.scalapfui.javafx.attribute.FXAttribute

import scala.language.implicitConversions

case class Text(property: JFXProperty[String])

object Text {

  val text: Attribute[String, Text] = FXAttribute[String, Text](_.property)

  implicit def text2LabeledMod[T <: Labeled](mod: Modifier[String, Text]): Modifier[String, T] =
    mod.mapApply[Labeled](native => Text(native.textProperty()))


  implicit def text2TextInputControlMod[T <: TextInputControl](
                                                                mod: Modifier[String, Text]
                                                              ): Modifier[String, T] =
    mod.mapApply[T](native => Text(native.textProperty()))

  implicit def text2MenuItemMod[T <: MenuItem](
                                                mod: Modifier[String, Text]
                                              ): Modifier[String, T] =
    mod.mapApply[T](native => Text(native.textProperty()))
}
