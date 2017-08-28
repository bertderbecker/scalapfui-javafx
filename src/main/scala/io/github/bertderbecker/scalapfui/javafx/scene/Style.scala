package io.github.bertderbecker.scalapfui.javafx.scene

import javafx.beans.property.StringProperty
import javafx.scene.Node
import javafx.scene.control.MenuItem

import io.github.bertderbecker.scalapfui.Modifier
import io.github.bertderbecker.scalapfui.attribute.Attribute
import io.github.bertderbecker.scalapfui.javafx.attribute.FXAttribute

case class Style(property: StringProperty)

object Style {

  val style: Attribute[String, Style] = FXAttribute[String, Style](_.property)

  implicit def style2nodeMod(mod: Modifier[String, Style]): Modifier[String, Node] =
    mod.mapApply(native => Style(native.styleProperty()))

  implicit def style2menuItemMod(mod: Modifier[String, Style]): Modifier[String, MenuItem] =
    mod.mapApply(native => Style(native.styleProperty()))
}
