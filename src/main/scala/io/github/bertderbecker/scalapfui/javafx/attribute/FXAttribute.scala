package io.github.bertderbecker.scalapfui.javafx.attribute

import io.github.bertderbecker.scalapfui.attribute.Attribute
import io.github.bertderbecker.scalapfui.property.Property
import javafx.beans.property.{Property => JFXProperty}

import io.github.bertderbecker.scalapfui.javafx.property.FXProperty

object FXAttribute extends AttributeCompanion[Attribute, JFXProperty] {

  def apply[T, Native](
                        propertyExtractor: Native => JFXProperty[T]
                      ): Attribute[T, Native] =

    new Attribute[T, Native] {
      override def propertyExtr: Native => Property[T] = native =>
        FXProperty(propertyExtractor(native))
    }

  def fromProperty[T, Native](
                        propertyExtractor: Native => Property[T]
                      ): Attribute[T, Native] =

    new Attribute[T, Native] {
      override def propertyExtr: Native => Property[T] = propertyExtractor
    }
}