package io.github.bertderbecker.scalapfui.javafx.scene.control

import javafx.scene.control.{Labeled, TextInputControl => JFXTextInputControl}

import io.github.bertderbecker.scalapfui.attribute.Attribute
import io.github.bertderbecker.scalapfui.javafx.attribute.FXAttribute


//NOT READY!! //TODO: make ready
object TextInputControlExts {

  trait SharedAttributes {

    val text: Attribute[String, JFXTextInputControl] =
      FXAttribute[String, JFXTextInputControl](_.textProperty())
  }

  object textInputControl extends SharedAttributes

}
