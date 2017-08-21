package io.github.bertderbecker.scalapfui.javafx.scene.control

import javafx.beans.property.SimpleObjectProperty
import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.event.ActionEvent
import javafx.scene.control.TextField
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

import io.github.bertderbecker.scalapfui.attribute.Attribute
import io.github.bertderbecker.scalapfui.javafx.attribute.FXAttribute
import io.github.bertderbecker.scalapfui.javafx.Implicits._
import io.github.bertderbecker.scalapfui.javafx.{FXElement, FXElementTag}

//Ready
object TextFieldExts {

  implicit class propertyImplicits[Element](prop: ObservableValue[Element]) {
    def onChange(u: Element => Unit): Unit = prop.addListener(new ChangeListener[Element] {
      override def changed(observable: ObservableValue[_ <: Element],
                           oldValue: Element,
                           newValue: Element): Unit = u(newValue)
    })
  }

  val TextField: FXElementTag[TextField] = FXElementTag(new TextField())


  //TODO: extract onAction for multi-extending
  val onAction: Attribute[ActionEvent => FXElement[_ <: Parent], TextField] =
    FXAttribute[ActionEvent => FXElement[_ <: Parent], TextField] { native =>
      val p = new SimpleObjectProperty[ActionEvent => FXElement[_ <: Parent]]()

      p.onChange { newValue =>
        native
          .onActionProperty()
          .setValue { event =>
            native
              .getScene
              .getWindow
              .asInstanceOf[Stage]
              .setScene(
                new Scene(
                  newValue(event).render
                )
              )
          }

      }
      p
    }

  val prefColumnCount: Attribute[Int, TextField] = FXAttribute[java.lang.Integer, TextField](_.prefColumnCountProperty().asObject())


  //TODO: support supertypes
}
