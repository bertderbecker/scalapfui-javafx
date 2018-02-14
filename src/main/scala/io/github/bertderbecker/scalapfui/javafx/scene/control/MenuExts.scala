package io.github.bertderbecker.scalapfui.javafx.scene.control

import javafx.event.Event
import javafx.scene.control.{MenuItem, Menu => JFXMenu}
import javafx.scene.{Parent => JFXParent}

import io.github.bertderbecker.scalapfui.attribute.{Attribute, ReadableAttribute}
import io.github.bertderbecker.scalapfui.javafx.Implicits._
import io.github.bertderbecker.scalapfui.javafx.attribute.{FXAttribute, FXReadableAttribute}
import io.github.bertderbecker.scalapfui.javafx.event.EventReactor
import io.github.bertderbecker.scalapfui.javafx.{FXElement, FXElementTag}

import scala.language.{higherKinds, implicitConversions}

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

  trait Attributes extends MenuItemExts.Attributes {

    val onHidden: Attribute[EventReactor[Event], JFXMenu] =
      FXAttribute.forEventReactor(_.onHiddenProperty())

    val onHiding: Attribute[EventReactor[Event], JFXMenu] =
      FXAttribute.forEventReactor(_.onHidingProperty())

    val onShowing: Attribute[EventReactor[Event], JFXMenu] =
      FXAttribute.forEventReactor(_.onShowingProperty())

    val onShown: Attribute[EventReactor[Event], JFXMenu] =
      FXAttribute.forEventReactor(_.onShownProperty())

    val showing: ReadableAttribute[Boolean, JFXMenu] =
      FXReadableAttribute[java.lang.Boolean, JFXMenu](_.showingProperty())
  }

  object menu extends Attributes

}
