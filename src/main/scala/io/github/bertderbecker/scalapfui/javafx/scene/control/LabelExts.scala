package io.github.bertderbecker.scalapfui.javafx.scene.control

import javafx.collections.ObservableList
import javafx.scene.control.Label
import javafx.scene.{Node, Parent}

import io.github.bertderbecker.scalapfui.javafx.{FXElement, FXElementTag}

//Ready
object LabelExts {

  trait LabelInstances {

    implicit class UILabelOps(element: FXElement[Label]) {
      def labelFor[N <: Node](other: FXElement[N]): FXElement[LabeledPair[N]] =
        new FXElement[LabeledPair[N]](() => LabeledPair(element, other)) {
          override def render: LabeledPair[N] = {
            val p = pure()
            p.label.pure().setLabelFor(other.pure())
            p
          }
        }
    }

  }

  val Label: FXElementTag[Label] = FXElementTag[Label](() => new Label())


  case class LabeledPair[N <: Node](label: FXElement[Label], other: FXElement[N]) extends Parent {

    def addToParent(childList: ObservableList[Node]): Unit = {
      val lab = label.render
      val oth = other.render
      lab.setLabelFor(oth)
      childList.addAll(lab, oth)
    }
  }

}
