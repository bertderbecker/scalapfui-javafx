package io.github.bertderbecker.scalapfui.javafx

import io.github.bertderbecker.scalapfui.{Element, Modifier}


class FXElement[Native](val pureElement: () => Native,
                        override val modifiers: Modifier[_, Native]*) extends Element[Native] {

  def elementWithAddedModifier(m: Modifier[_, Native]) =
    new FXElement[Native](pureElement, modifiers :+ m: _*)

  override val pure: () => Native = pureElement

  def withChangedPure[N >: Native](newPure: => N) =
    new FXElement[N](() => newPure, modifiers.map(_.asInstanceOf[Modifier[_, N]]): _*)
}

object FXElement {

  def wrap[N](pure: => N): FXElement[N] = {
    FXElementTag(() => pure).apply()
  }

}