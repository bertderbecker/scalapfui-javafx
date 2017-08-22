package io.github.bertderbecker.scalapfui.javafx.stage

import javafx.event.EventDispatcher
import javafx.stage.Window

import io.github.bertderbecker.scalapfui.javafx.Implicits._
import io.github.bertderbecker.scalapfui.attribute.{Attribute, ReadableAttribute}
import io.github.bertderbecker.scalapfui.javafx.attribute.{FXAttribute, FXReadableAttribute}

object WindowExts {

  val eventDispatcher: Attribute[EventDispatcher, Window] =
    FXAttribute[EventDispatcher, Window](_.eventDispatcherProperty())


  val focused: ReadableAttribute[Boolean, Window] =
    FXReadableAttribute[java.lang.Boolean, Window](_.focusedProperty().asObject())

  val height: ReadableAttribute[Double, Window] =
    FXReadableAttribute[java.lang.Double, Window](_.heightProperty().asObject())


}
