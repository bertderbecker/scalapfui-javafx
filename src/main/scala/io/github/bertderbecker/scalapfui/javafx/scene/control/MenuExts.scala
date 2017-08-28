package io.github.bertderbecker.scalapfui.javafx.scene.control

import javafx.scene.control.{MenuItem, Menu => JFXMenu}

import io.github.bertderbecker.scalapfui.attribute.ReadableAttribute
import io.github.bertderbecker.scalapfui.javafx.Implicits._
import io.github.bertderbecker.scalapfui.javafx.attribute.FXReadableAttribute
import io.github.bertderbecker.scalapfui.javafx.{FXElement, FXElementTag}

object MenuExts {

  val Menu: (FXElement[_ <: MenuItem] *) => FXElementTag[JFXMenu] =
    (items: Seq[FXElement[_ <: MenuItem]]) =>
      FXElementTag(
        () => {
          val m = new JFXMenu()
          m.getItems.addAll(items.map(_.render): _*)
          m
        }
      )


  val showing: ReadableAttribute[Boolean, JFXMenu] =
    FXReadableAttribute[java.lang.Boolean, JFXMenu](_.showingProperty())

}
