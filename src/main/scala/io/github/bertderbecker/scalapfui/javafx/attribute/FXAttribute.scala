package io.github.bertderbecker.scalapfui.javafx.attribute

import javafx.beans.property.{SimpleObjectProperty, Property => JFXProperty}
import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.event.{Event, EventHandler}
import javafx.scene.{Parent => JFXParent}

import io.github.bertderbecker.scalapfui.attribute.Attribute
import io.github.bertderbecker.scalapfui.javafx.FXElement
import io.github.bertderbecker.scalapfui.javafx.Implicits._
import io.github.bertderbecker.scalapfui.javafx.event.EventReactor
import io.github.bertderbecker.scalapfui.javafx.property.FXProperty
import io.github.bertderbecker.scalapfui.property.Property

object FXAttribute extends AttributeCompanion[Attribute, JFXProperty] {

  implicit class propertyImplicits[Element](prop: ObservableValue[Element]) {
    def onChange(u: Element => Unit): Unit = prop.addListener(new ChangeListener[Element] {
      override def changed(observable: ObservableValue[_ <: Element],
                           oldValue: Element,
                           newValue: Element): Unit = u(newValue)
    })
  }


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

  def forEventReactor[E <: Event, Native](property: Native => JFXProperty[EventHandler[E]]): Attribute[EventReactor[E], Native] =

    FXAttribute.apply[EventReactor[E], Native] { native =>
      val p = new SimpleObjectProperty[EventReactor[E]]()
      p.onChange { newValue =>
        property(native).setValue(newValue.op(_))
      }
      p
    }

  def forEventReactorConformist[E <: Event, Native](property: Native => JFXProperty[_ >: EventHandler[E]]): Attribute[EventReactor[E], Native] =

    FXAttribute.apply[EventReactor[E], Native] { native =>
      val p = new SimpleObjectProperty[EventReactor[E]]()
      p.onChange { newValue =>
        property(native).setValue(new EventHandler[E] {
          override def handle(event: E): Unit = newValue.op(event)
        })
      }
      p
    }

}