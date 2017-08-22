package io.github.bertderbecker.scalapfui.javafx.attribute

import io.github.bertderbecker.scalapfui.attribute.Attribute
import io.github.bertderbecker.scalapfui.property.Property
import javafx.beans.property.{SimpleObjectProperty, Property => JFXProperty}
import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.event.{Event, EventHandler}
import javafx.scene.{Node, Scene, Parent => JFXParent}
import javafx.stage.Stage

import io.github.bertderbecker.scalapfui.javafx.FXElement
import io.github.bertderbecker.scalapfui.javafx.property.FXProperty

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

  def forEventHandler[T <: Event, Native <: Node](
                                            op: Native => JFXProperty[EventHandler[T]]
                                          ): Attribute[T => FXElement[_ <: JFXParent], Native] = {
    apply[T => FXElement[_ <: JFXParent], Native] { native =>
      val p = new SimpleObjectProperty[T => FXElement[_ <: JFXParent]]()
      p.onChange { newValue =>
        op(native).setValue { event =>
          //println("Action!!")
          val stage = native
            .getScene
            .getWindow
            .asInstanceOf[Stage]
          stage.setScene(new Scene(newValue(event).render))
        }
      }
      p
    }
  }

}