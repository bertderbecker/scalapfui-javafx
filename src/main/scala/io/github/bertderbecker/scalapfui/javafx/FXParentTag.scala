package io.github.bertderbecker.scalapfui.javafx

import javafx.collections.ObservableList
import javafx.scene.{Node, Parent => JFXParent}

import io.github.bertderbecker.scalapfui.{Element, Modifier, ParentTag}

case class FXParentTag[Native <: JFXParent](pure: () => Native,
                                            childrenExtr: Native => ObservableList[Node]
                                           ) extends ParentTag[Native, Node, JFXParent] {

  type ElementImpl[N] = FXElement[N]

  type ParentImpl[N <: JFXParent] = FXParent[N]

  override def apply(children: ElementImpl[_ <: Node]*)(modifiers: Modifier[_, Native]*): ParentImpl[Native] =
    new FXParent[Native](pure, childrenExtr)(modifiers: _*)(children: _*)

}