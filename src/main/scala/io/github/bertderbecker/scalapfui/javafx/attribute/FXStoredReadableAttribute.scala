package io.github.bertderbecker.scalapfui.javafx.attribute

import javafx.beans.property.SimpleObjectProperty

import io.github.bertderbecker.scalapfui.attribute.StoredReadableAttribute
import io.github.bertderbecker.scalapfui.javafx.property.FXProperty

case class FXStoredReadableAttribute[T]() extends StoredReadableAttribute[T] {

  override val property = FXProperty.apply(new SimpleObjectProperty[T]())

}
