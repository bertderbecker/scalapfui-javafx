package io.github.bertderbecker.scalapfui.javafx.scene.control

import javafx.scene.control.{Menu, MenuBar => JFXMenuBar}

import io.github.bertderbecker.scalapfui.attribute.Attribute
import io.github.bertderbecker.scalapfui.javafx.Implicits._
import io.github.bertderbecker.scalapfui.javafx.attribute.FXAttribute
import io.github.bertderbecker.scalapfui.javafx.{FXElement, FXElementTag}

//Ready
object MenuBarExts {

  def MenuBar(elements: FXElement[_ <: Menu]*): FXElementTag[JFXMenuBar] =
    FXElementTag(() => new JFXMenuBar(elements.map(_.render): _*))


  trait Attributes extends ControlExts.Attributes {

    val useSystemMenuBar: Attribute[Boolean, JFXMenuBar] =
      FXAttribute[java.lang.Boolean, JFXMenuBar](_.useSystemMenuBarProperty())
  }

  object menuBar extends Attributes

}
