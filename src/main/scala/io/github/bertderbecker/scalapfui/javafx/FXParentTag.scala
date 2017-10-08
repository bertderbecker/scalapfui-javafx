package io.github.bertderbecker.scalapfui.javafx

import javafx.collections.ObservableList
import javafx.scene.{Node, Parent => JFXParent}

import io.github.bertderbecker.scalapfui.{Element, Modifier, ParentTag}

case class FXParentTag[Native <: JFXParent](pure: () => Native, childrenExtr: Native => ObservableList[Node]) extends ParentTag[Native, Node] {

  override def apply(children: Element[_ <: Node]*)(modifiers: Modifier[_, _ >: Native]*): FXParent[Native] =
    new FXParent[Native](pure, childrenExtr)(modifiers: _*)(children: _*)
}