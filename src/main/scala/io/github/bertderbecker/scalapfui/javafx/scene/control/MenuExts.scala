package io.github.bertderbecker.scalapfui.javafx.scene.control

import javafx.event.Event
import javafx.scene.control.{MenuItem, Menu => JFXMenu}

import io.github.bertderbecker.scalapfui.attribute.{Attribute, ReadableAttribute}
import io.github.bertderbecker.scalapfui.javafx.Implicits._
import io.github.bertderbecker.scalapfui.javafx.attribute.{FXAttribute, FXReadableAttribute}
import javafx.scene.{Parent => JFXParent}

import io.github.bertderbecker.scalapfui.javafx.property.FXProperty
import io.github.bertderbecker.scalapfui.javafx.{FXElement, FXElementTag}

object MenuExts {

  val Menu: (FXElement[_ <: MenuItem]*) => FXElementTag[JFXMenu] =
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

  trait SharedAttributes extends MenuItemExts.SharedAttributes {

    val onHidden: Attribute[Event => FXElement[_ <: JFXParent], JFXMenu] =
      FXAttribute.forEventHandlerUnwrapped(x => FXProperty(x.onHiddenProperty()))

    val onHiding: Attribute[Event => FXElement[_ <: JFXParent], JFXMenu] =
      FXAttribute.forEventHandlerUnwrapped(x => FXProperty(x.onHidingProperty()))

    val onShowing: Attribute[Event => FXElement[_ <: JFXParent], JFXMenu] =
      FXAttribute.forEventHandlerUnwrapped(x => FXProperty(x.onShowingProperty()))

    val onShown: Attribute[Event => FXElement[_ <: JFXParent], JFXMenu] =
      FXAttribute.forEventHandlerUnwrapped(x => FXProperty(x.onShownProperty()))

  }

  object menu extends SharedAttributes

}
