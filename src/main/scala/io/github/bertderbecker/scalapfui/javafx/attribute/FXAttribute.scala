package io.github.bertderbecker.scalapfui.javafx.attribute

import javafx.beans.property.{SimpleObjectProperty, Property => JFXProperty}
import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.event.{Event, EventHandler}
import javafx.scene.{Scene, Parent => JFXParent}
import javafx.stage.{Stage, Window}

import io.github.bertderbecker.scalapfui.attribute.Attribute
import io.github.bertderbecker.scalapfui.javafx.{FXElement, JFXApp}
import io.github.bertderbecker.scalapfui.javafx.property.FXProperty
import io.github.bertderbecker.scalapfui.property.Property
import io.github.bertderbecker.scalapfui.javafx.Implicits._

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

  def forEventHandlerUnwrapped[T <: Event, Native](
                                                    op: Native => Property[EventHandler[T]]
                                         ): Attribute[T => FXElement[_ <: JFXParent], Native] = {
    apply[T => FXElement[_ <: JFXParent], Native] { native =>
      val p = new SimpleObjectProperty[T => FXElement[_ <: JFXParent]]()
      p.onChange { newValue =>
        op(native).update { event =>
          println("Action!!")
          val stage = JFXApp.Stage
          println("Stage = " + stage)
          stage.setScene(new Scene(newValue(event).render))
        }
      }
      p
    }
  }

  def forEventHandler[T <: Event, Native](
                                           op: Native => JFXProperty[_ >: EventHandler[_ >: T <: Event]]
                                         ): Attribute[T => FXElement[_ <: JFXParent], Native] =
    forEventHandlerUnwrapped[T, Native](native =>
      propertyInvariant.imap(FXProperty(op(native))
      )(_.asInstanceOf[EventHandler[T]]
      )(_.asInstanceOf[EventHandler[_ >: T <: Event]]))

}