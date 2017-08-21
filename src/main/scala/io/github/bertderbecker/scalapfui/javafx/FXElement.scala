package io.github.bertderbecker.scalapfui.javafx

import io.github.bertderbecker.scalapfui.{Element, Modifier}


class FXElement[Native](val pure: Native, override val modifiers: Modifier[_, Native]*) extends Element[Native] {

  def withChangedPureElement[N >: Native](newPure: N) =
    new FXElement[N](newPure, modifiers.map(_.asInstanceOf[Modifier[_, N]]): _*)

}

object FXElement {

  def wrap[N](pure: N): FXElement[N] = {
    FXElementTag(pure).apply()
  }

}