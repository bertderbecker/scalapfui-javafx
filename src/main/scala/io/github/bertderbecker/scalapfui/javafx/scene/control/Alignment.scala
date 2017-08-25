package io.github.bertderbecker.scalapfui.javafx.scene.control

import javafx.beans.property.{Property => JFXProperty}
import javafx.geometry.Pos
import javafx.scene.control.{Labeled, TextField}
import javafx.scene.layout.VBox

import io.github.bertderbecker.scalapfui.Modifier
import io.github.bertderbecker.scalapfui.attribute.Attribute
import io.github.bertderbecker.scalapfui.javafx.attribute.FXAttribute

import scala.language.implicitConversions

case class Alignment(property: JFXProperty[Pos])

object Alignment {

  val alignment: Attribute[Pos, Alignment] = FXAttribute.apply[Pos, Alignment](_.property)

  object Alignment {

    val BaselineCenter: Modifier[Pos, Alignment] = alignment := Pos.BASELINE_CENTER
    val BaselineLeft: Modifier[Pos, Alignment] = alignment := Pos.BASELINE_LEFT
    val BaselineRight: Modifier[Pos, Alignment] = alignment := Pos.BASELINE_RIGHT
    val BottomCenter: Modifier[Pos, Alignment] = alignment := Pos.BOTTOM_CENTER
    val BottomLeft: Modifier[Pos, Alignment] = alignment := Pos.BOTTOM_LEFT
    val BottomRight: Modifier[Pos, Alignment] = alignment := Pos.BOTTOM_RIGHT
    val Center: Modifier[Pos, Alignment] = alignment := Pos.CENTER
    val CenterLeft: Modifier[Pos, Alignment] = alignment := Pos.CENTER_LEFT
    val CenterRight: Modifier[Pos, Alignment] = alignment := Pos.CENTER_RIGHT
    val TopCenter: Modifier[Pos, Alignment] = alignment := Pos.TOP_CENTER
    val TopLeft: Modifier[Pos, Alignment] = alignment := Pos.TOP_LEFT
    val TopRight: Modifier[Pos, Alignment] = alignment := Pos.TOP_RIGHT
  }

  implicit def text2LabeledMod[T <: Labeled](mod: Modifier[Pos, Alignment]): Modifier[Pos, T] =
    mod.mapApply[Labeled](native => new Alignment(native.alignmentProperty()))

  implicit def text2TextFieldlMod[T <: TextField](mod: Modifier[Pos, Alignment]): Modifier[Pos, T] =
    mod.mapApply[TextField](native => new Alignment(native.alignmentProperty()))

  implicit def text2VBoxMod[T <: VBox](mod: Modifier[Pos, Alignment]): Modifier[Pos, T] =
    mod.mapApply[VBox](native => new Alignment(native.alignmentProperty()))

}