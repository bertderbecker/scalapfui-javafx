package io.github.bertderbecker.scalapfui.javafx.scene.control

import javafx.scene.Node
import javafx.scene.control.{Labeled, OverrunStyle, ContentDisplay => JFXContentDisplay}
import javafx.scene.paint.Paint
import javafx.scene.text.{Font, TextAlignment => JFXTextAlignment}

import io.github.bertderbecker.scalapfui.Modifier
import io.github.bertderbecker.scalapfui.attribute.{Attribute, ReadableAttribute}
import io.github.bertderbecker.scalapfui.javafx.Implicits._
import io.github.bertderbecker.scalapfui.javafx.attribute.{FXAttribute, FXReadableAttribute}
import io.github.bertderbecker.scalapfui.javafx.geometry.Insets

//Ready
object LabeledExts {

  //Alignment is ready

  val contentDisplay: Attribute[JFXContentDisplay, Labeled] =
    FXAttribute[JFXContentDisplay, Labeled](_.contentDisplayProperty())

  object ContentDisplay {
    val Bottom: Modifier[JFXContentDisplay, Labeled] = contentDisplay := JFXContentDisplay.BOTTOM
    val Center: Modifier[JFXContentDisplay, Labeled] = contentDisplay := JFXContentDisplay.CENTER
    val GraphicOnly: Modifier[JFXContentDisplay, Labeled] = contentDisplay := JFXContentDisplay.GRAPHIC_ONLY
    val Left: Modifier[JFXContentDisplay, Labeled] = contentDisplay := JFXContentDisplay.LEFT
    val Right: Modifier[JFXContentDisplay, Labeled] = contentDisplay := JFXContentDisplay.RIGHT
    val TextOnly: Modifier[JFXContentDisplay, Labeled] = contentDisplay := JFXContentDisplay.TEXT_ONLY
    val Top: Modifier[JFXContentDisplay, Labeled] = contentDisplay := JFXContentDisplay.TOP
  }

  val ellipsisString: Attribute[String, Labeled] = FXAttribute[String, Labeled](_.ellipsisStringProperty())

  //TODO: support Font
  val font: Attribute[Font, Labeled] = FXAttribute[Font, Labeled](_.fontProperty())

  val graphic: Attribute[Node, Labeled] = FXAttribute[Node, Labeled](_.graphicProperty())

  val graphicTextGap: Attribute[Double, Labeled] =
    FXAttribute[java.lang.Double, Labeled](_.graphicTextGapProperty().asObject())

  val labelPadding: ReadableAttribute[Insets, Labeled] = FXReadableAttribute[javafx.geometry.Insets, Labeled](_.labelPaddingProperty())

  val lineSpacing: Attribute[Double, Labeled] = FXAttribute[java.lang.Double, Labeled](_.lineSpacingProperty().asObject())

  val mnemonicParsing: Attribute[Boolean, Labeled] = FXAttribute[java.lang.Boolean, Labeled](_.mnemonicParsingProperty())

  val textAlignment: Attribute[JFXTextAlignment, Labeled] = FXAttribute[JFXTextAlignment, Labeled](_.textAlignmentProperty())

  object TextAlignment {
    val Center: Modifier[JFXTextAlignment, Labeled] = textAlignment := JFXTextAlignment.CENTER
    val Justify: Modifier[JFXTextAlignment, Labeled] = textAlignment := JFXTextAlignment.JUSTIFY
    val Left: Modifier[JFXTextAlignment, Labeled] = textAlignment := JFXTextAlignment.LEFT
    val Right: Modifier[JFXTextAlignment, Labeled] = textAlignment := JFXTextAlignment.RIGHT
  }

  val textFill: Attribute[Paint, Labeled] = FXAttribute[Paint, Labeled](_.textFillProperty())

  //TODO: support OverrunStyle
  val textOverrun: Attribute[OverrunStyle, Labeled] = FXAttribute[OverrunStyle, Labeled](_.textOverrunProperty())

  //Text is ready

  val underline: Attribute[Boolean, Labeled] = FXAttribute[java.lang.Boolean, Labeled](_.underlineProperty().asObject())

  val wrapText: Attribute[Boolean, Labeled] = FXAttribute[java.lang.Boolean, Labeled](_.wrapTextProperty().asObject())

  //TODO: support supertypes of Labeled

}
