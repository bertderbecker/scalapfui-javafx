package io.github.bertderbecker.scalapfui.javafx.scene.control

import javafx.event.ActionEvent
import javafx.geometry.Pos
import javafx.scene.control.TextField
import javafx.scene.{Parent => JFXParent}

import io.github.bertderbecker.scalapfui.Modifier
import io.github.bertderbecker.scalapfui.attribute.Attribute
import io.github.bertderbecker.scalapfui.javafx.Implicits._
import io.github.bertderbecker.scalapfui.javafx.attribute.FXAttribute
import io.github.bertderbecker.scalapfui.javafx.property.FXProperty
import io.github.bertderbecker.scalapfui.javafx.{FXElement, FXElementTag}

//Ready
object TextFieldExts {


  val TextField: FXElementTag[TextField] = FXElementTag(() => new TextField())


  val prefColumnCount: Attribute[Int, TextField] = FXAttribute[java.lang.Integer, TextField](_.prefColumnCountProperty().asObject())

  trait SharedAttributes extends TextInputControlExts.SharedAttributes

  object textField extends SharedAttributes {

    //not sure if it works
    lazy val onAction: Attribute[(ActionEvent) => FXElement[_ <: JFXParent], TextField] =
      FXAttribute.forEventHandlerUnwrapped(x => FXProperty(x.onActionProperty()))


    val alignment: Attribute[Pos, TextField] = FXAttribute.apply[Pos, TextField](_.alignmentProperty())

    object Alignment {
      val BaselineCenter: Modifier[Pos, TextField] = alignment := Pos.BASELINE_CENTER
      val BaselineLeft: Modifier[Pos, TextField] = alignment := Pos.BASELINE_LEFT
      val BaselineRight: Modifier[Pos, TextField] = alignment := Pos.BASELINE_RIGHT
      val BottomCenter: Modifier[Pos, TextField] = alignment := Pos.BOTTOM_CENTER
      val BottomLeft: Modifier[Pos, TextField] = alignment := Pos.BOTTOM_LEFT
      val BottomRight: Modifier[Pos, TextField] = alignment := Pos.BOTTOM_RIGHT
      val Center: Modifier[Pos, TextField] = alignment := Pos.CENTER
      val CenterLeft: Modifier[Pos, TextField] = alignment := Pos.CENTER_LEFT
      val CenterRight: Modifier[Pos, TextField] = alignment := Pos.CENTER_RIGHT
      val TopCenter: Modifier[Pos, TextField] = alignment := Pos.TOP_CENTER
      val TopLeft: Modifier[Pos, TextField] = alignment := Pos.TOP_LEFT
      val TopRight: Modifier[Pos, TextField] = alignment := Pos.TOP_RIGHT
    }
  }

  //TODO: support supertypes
}
