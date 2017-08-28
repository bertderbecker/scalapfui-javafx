package io.github.bertderbecker.scalapfui.javafx.scene.control

import javafx.scene.control.{MenuBar => JFXMenuBar}

import io.github.bertderbecker.scalapfui.attribute.Attribute
import io.github.bertderbecker.scalapfui.javafx.FXElementTag
import io.github.bertderbecker.scalapfui.javafx.Implicits._
import io.github.bertderbecker.scalapfui.javafx.FXElement
import javafx.scene.control.Menu
import io.github.bertderbecker.scalapfui.javafx.attribute.FXAttribute

//Ready
object MenuBarExts {


  val MenuBar: (FXElement[_ <: Menu] *) => FXElementTag[JFXMenuBar] =
    (elements: Seq[FXElement[_ <: Menu]]) => FXElementTag(() => new JFXMenuBar(elements.map(_.render): _*))

  val useSystemMenuBar: Attribute[Boolean, JFXMenuBar] =
    FXAttribute[java.lang.Boolean, JFXMenuBar](_.useSystemMenuBarProperty())


}
