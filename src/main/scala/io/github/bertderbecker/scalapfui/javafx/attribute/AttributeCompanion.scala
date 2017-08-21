package io.github.bertderbecker.scalapfui.javafx.attribute

import scala.language.higherKinds

private[attribute] trait AttributeCompanion[A[_, _], Prop[_]] {

  def apply[T, Native](propertyExtr: Native => Prop[T]): A[T, Native]

}
