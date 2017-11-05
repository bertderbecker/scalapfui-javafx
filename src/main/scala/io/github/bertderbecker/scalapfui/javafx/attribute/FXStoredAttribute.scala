package io.github.bertderbecker.scalapfui.javafx.attribute

import javafx.beans.property.SimpleObjectProperty

import io.github.bertderbecker.scalapfui.attribute.StoredAttribute
import io.github.bertderbecker.scalapfui.javafx.property.FXProperty

case class FXStoredAttribute[T](override val initValue: Option[T]) extends StoredAttribute[T] {

  override val property = FXProperty.apply(new SimpleObjectProperty[T]())

}

object FXStoredAttribute {

  def apply[T](initValue: T): FXStoredAttribute[T] = FXStoredAttribute(Some(initValue))
  def apply[T](): FXStoredAttribute[T] = FXStoredAttribute(None)

}
