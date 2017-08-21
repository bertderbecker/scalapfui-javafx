package io.github.bertderbecker.scalapfui.javafx.scene.control

import javafx.beans.property.{Property => JFXProperty}
import javafx.scene.control.{Labeled, TextInputControl}

import io.github.bertderbecker.scalapfui.Modifier
import io.github.bertderbecker.scalapfui.attribute.Attribute
import io.github.bertderbecker.scalapfui.javafx.attribute.FXAttribute

import scala.language.implicitConversions

case class Text(property: JFXProperty[String])

object Text {

  val text: Attribute[String, Text] = FXAttribute[String, Text](_.property)

  implicit def text2LabeledMod(mod: Modifier[String, Text]): Modifier[String, Labeled] =
    mod.mapApply[Labeled](native => Text(native.textProperty()))


  implicit def text2TextInputControlMod(mod: Modifier[String, Text]): Modifier[String, TextInputControl] =
    mod.mapApply[TextInputControl](native => Text(native.textProperty()))
}
