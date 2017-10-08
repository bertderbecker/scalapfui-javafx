package io.github.bertderbecker.scalapfui.javafx.property

import javafx.beans.property.{Property => JFXProperty}
import javafx.beans.value.WritableValue

import io.github.bertderbecker.scalapfui.property.{NestedWritableProperty, Property, WritableProperty}


case class FXWritableProperty[T](prop: WritableValue[T]) extends WritableProperty[T] {

  override def doUpdate(newValue: T): Unit = prop.setValue(newValue)

}

object FXWritableProperty {

  def apply[T](writableValue: WritableValue[T]): WritableProperty[T] =
    new FXWritableProperty(writableValue)

  trait WritablePropertyInstances {

  }

}