package io.github.bertderbecker.scalapfui.javafx.scene.control

import javafx.event.ActionEvent
import javafx.scene.control.TextField
import javafx.scene.{Parent => JFXParent}

import io.github.bertderbecker.scalapfui.attribute.Attribute
import io.github.bertderbecker.scalapfui.javafx.Implicits._
import io.github.bertderbecker.scalapfui.javafx.attribute.FXAttribute
import io.github.bertderbecker.scalapfui.javafx.{FXElement, FXElementTag}

//Ready
object TextFieldExts {


  val TextField: FXElementTag[TextField] = FXElementTag(() => new TextField())


  val prefColumnCount: Attribute[Int, TextField] = FXAttribute[java.lang.Integer, TextField](_.prefColumnCountProperty().asObject())


  //TODO: support supertypes
}
