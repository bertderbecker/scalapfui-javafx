package io.github.bertderbecker.scalapfui.javafx.scene.control

import javafx.scene.control.{ContextMenu, Skin, Skinnable, Tooltip, Control => JFXControl}

import io.github.bertderbecker.scalapfui.attribute.Attribute
import io.github.bertderbecker.scalapfui.javafx.attribute.FXAttribute

object ControlExts {

  val contextMenu: Attribute[ContextMenu, JFXControl] =
    FXAttribute[ContextMenu, JFXControl](_.contextMenuProperty())

  val skin: Attribute[Skin[_ <: Skinnable], JFXControl] =
    FXAttribute[Skin[_ <: Skinnable], JFXControl](_.skinProperty())

  val tooltip: Attribute[Tooltip, JFXControl] =
    FXAttribute[Tooltip, JFXControl](_.tooltipProperty())

}
