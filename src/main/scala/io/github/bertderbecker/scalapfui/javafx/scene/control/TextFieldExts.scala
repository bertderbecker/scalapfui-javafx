package io.github.bertderbecker.scalapfui.javafx.scene.control

import javafx.beans.property.SimpleObjectProperty
import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.event.ActionEvent
import javafx.scene.control.TextField
import javafx.scene.{Parent => JFXParent, Scene}
import javafx.stage.Stage

import io.github.bertderbecker.scalapfui.attribute.Attribute
import io.github.bertderbecker.scalapfui.javafx.attribute.FXAttribute
import FXAttribute._
import io.github.bertderbecker.scalapfui.javafx.Implicits._
import io.github.bertderbecker.scalapfui.javafx.{FXElement, FXElementTag}

//Ready
object TextFieldExts {


  val TextField: FXElementTag[TextField] = FXElementTag(() => new TextField())


  //TODO: extract onAction for multi-extending
  val onAction: Attribute[ActionEvent => FXElement[_ <: JFXParent], TextField] =
    FXAttribute.forEventHandler(_.onActionProperty())



  val prefColumnCount: Attribute[Int, TextField] = FXAttribute[java.lang.Integer, TextField](_.prefColumnCountProperty().asObject())


  //TODO: support supertypes
}
