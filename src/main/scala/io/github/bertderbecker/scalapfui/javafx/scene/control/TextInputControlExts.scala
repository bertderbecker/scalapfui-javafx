package io.github.bertderbecker.scalapfui.javafx.scene.control

import javafx.scene.control.{TextInputControl => JFXTextInputControl}

import io.github.bertderbecker.scalapfui.attribute.Attribute
import io.github.bertderbecker.scalapfui.javafx.attribute.FXAttribute


//NOT READY!! //TODO: make ready
object TextInputControlExts {

  trait Attributes extends ControlExts.Attributes{

    val text: Attribute[String, JFXTextInputControl] =
      FXAttribute[String, JFXTextInputControl](_.textProperty())
  }

  object textInputControl extends Attributes

}
