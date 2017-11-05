package io.github.bertderbecker.scalapfui.javafx

import io.github.bertderbecker.scalapfui.{ElementTag, Modifier}

case class FXElementTag[Native](pure: () => Native) extends ElementTag[Native] {

  type ElementImpl[N] = FXElement[N]

  override def apply(modifiers: Modifier[_, Native]*): ElementImpl[Native] =
    new FXElement[Native](pure, modifiers: _*)

}