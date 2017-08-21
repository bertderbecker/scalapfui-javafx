package io.github.bertderbecker.scalapfui.javafx

import javafx.collections.ObservableList
import javafx.scene.{Node, Parent => JFXParent}

import io.github.bertderbecker.scalapfui.javafx.scene.control.LabelExts.LabeledPair
import io.github.bertderbecker.scalapfui.{Element, Modifier, Parent}


class FXParent[Native <: JFXParent](pure: Native, val childrenExtr: Native => ObservableList[Node])
                                   (modifiers: Modifier[_, Native]*)
                                   (val children: Element[_ <: Node]*) extends FXElement[Native](pure, modifiers: _*) with Parent[Native] {

  lazy val childList: ObservableList[Node] = childrenExtr(pure)

  override def addChild[T](pure: Native, child: T): Unit = child match {
    case p: LabeledPair[_] => p.addToParent(childList)
    case n: Node => childList.add(n)
    case _ => throw new IllegalArgumentException("Child is not a node!")
  }
}

object FXParent {
  def wrap[Native <: JFXParent](pure: Native): FXParent[Native] =
    new FXParent[Native](pure, _.getChildrenUnmodifiable)()() {
      override def render: Native = pure
    }
}