package io.github.bertderbecker.scalapfui.javafx.attribute

import io.github.bertderbecker.scalapfui.Modifier
import io.github.bertderbecker.scalapfui.attribute.SimpleWritableAttribute

//TODO: find better name
case class FXSimpleWritableAttribute[T, Native](op: T => Native => Unit)
  extends SimpleWritableAttribute[T, Native] {

  def :=(x: T): Modifier[T, Native] = Modifier[T, Native](n => op(x)(n))

}