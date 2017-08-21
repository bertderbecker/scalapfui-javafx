package io.github.bertderbecker.scalapfui.javafx.property

import javafx.beans.property.{Property => JFXProperty}
import javafx.beans.value.WritableValue

import io.github.bertderbecker.scalapfui.property.NestedWritableProperty


case class FXWritableProperty[T](prop: WritableValue[T]) extends NestedWritableProperty[T] {

  override def doUpdate(newValue: T): Unit = prop.setValue(newValue)

}

object FXWritableProperty {

  def apply[T](writableValue: WritableValue[T]): NestedWritableProperty[T] =
    new FXWritableProperty(writableValue)

  trait WritablePropertyInstances {

  }

}