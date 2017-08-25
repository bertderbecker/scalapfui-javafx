package io.github.bertderbecker.scalapfui.javafx.scene

import io.github.bertderbecker.scalapfui.attribute.ReadableAttribute
import io.github.bertderbecker.scalapfui.javafx.Implicits._
import javafx.scene.{Parent => JFXParent}

import io.github.bertderbecker.scalapfui.javafx.attribute.FXReadableAttribute

object ParentExts {

  val needsLayout: ReadableAttribute[Boolean, JFXParent] =
    FXReadableAttribute[java.lang.Boolean, JFXParent](_.needsLayoutProperty())
}
