package io.github.bertderbecker.scalapfui.javafx

import io.github.bertderbecker.scalapfui.{ElementTag, Modifier}

case class FXElementTag[Native](pure: Native) extends ElementTag[Native] {
  override def apply(modifiers: Modifier[_, Native]*): FXElement[Native] =
    new FXElement[Native](pure, modifiers: _*)
}
