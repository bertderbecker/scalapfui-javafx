package io.github.bertderbecker.scalapfui.javafx.scene.layout

import javafx.scene.layout.Pane

import io.github.bertderbecker.scalapfui.javafx.FXParentTag

//Ready
object PaneExts {

  val Pane: FXParentTag[Pane] = FXParentTag[Pane](() => new Pane(), _.getChildren)

  trait SharedAttributes extends RegionExts.SharedAttributes {

  }

  object pane extends SharedAttributes
}
