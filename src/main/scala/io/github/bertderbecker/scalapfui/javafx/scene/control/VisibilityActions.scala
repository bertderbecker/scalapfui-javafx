package io.github.bertderbecker.scalapfui.javafx.scene.control

import javafx.event.{Event, EventHandler}
import javafx.scene.control.Menu
import javafx.scene.{Parent => JFXParent}
import javafx.stage.Window

import io.github.bertderbecker.scalapfui.Modifier
import io.github.bertderbecker.scalapfui.attribute.Attribute
import io.github.bertderbecker.scalapfui.javafx.FXElement
import io.github.bertderbecker.scalapfui.javafx.attribute.FXAttribute
import io.github.bertderbecker.scalapfui.javafx.property.FXProperty
import io.github.bertderbecker.scalapfui.property.Property

import scala.language.implicitConversions

object VisibilityActions {


  case class OnHidden[T <: Event](
                       property: Property[EventHandler[T]],
                       window: Window
                     )

  val onHidden: Attribute[Event => FXElement[_ <: JFXParent], OnHidden[Event]] =
    FXAttribute.forEventHandlerUnwrapped(_.property)

  implicit def onHidden2MenuMod(
                                 mod: Modifier[
                                   Event => FXElement[_ <: JFXParent],
                                   OnHidden[Event]
                                   ])
  : Modifier[Event => FXElement[_ <: JFXParent], Menu] =

    mod.mapApply(
      (native: Menu) =>
        OnHidden(
          FXProperty(native.onHiddenProperty()),
          //https://stackoverflow.com/questions/20594392/unable-to-get-scene-from-menuitem-in-javafx
          native.getParentPopup.getScene.getWindow
        )
    )


  case class OnHiding(
                       property: Property[EventHandler[Event]],
                       window: Window
                     )

  val onHiding: Attribute[Event => FXElement[_ <: JFXParent], OnHiding] =
    FXAttribute.forEventHandlerUnwrapped(_.property)

  implicit def onHiding2MenuMod(
                                 mod: Modifier[
                                   Event => FXElement[_ <: JFXParent],
                                   OnHiding
                                   ])
  : Modifier[Event => FXElement[_ <: JFXParent], Menu] =

    mod.mapApply(
      (native: Menu) =>
        OnHiding(
          FXProperty(native.onHidingProperty()),
          native.getParentPopup.getScene.getWindow
        )
    )


  case class OnShowing(
                        property: Property[EventHandler[Event]],
                        window: Window
                      )

  val onShowing: Attribute[Event => FXElement[_ <: JFXParent], OnShowing] =
    FXAttribute.forEventHandlerUnwrapped(_.property)

  implicit def onShowing2MenuMod(
                                  mod: Modifier[
                                    Event => FXElement[_ <: JFXParent],
                                    OnShowing
                                    ])
  : Modifier[Event => FXElement[_ <: JFXParent], Menu] =

    mod.mapApply(
      (native: Menu) =>
        OnShowing(
          FXProperty(native.onShowingProperty()),
          native.getParentPopup.getScene.getWindow
        )
    )


  case class OnShown(
                      property: Property[EventHandler[Event]],
                      window: Window
                    )

  val onShown: Attribute[Event => FXElement[_ <: JFXParent], OnShown] =
    FXAttribute.forEventHandlerUnwrapped(_.property)

  implicit def onShown2MenuMod(
                                mod: Modifier[
                                  Event => FXElement[_ <: JFXParent],
                                  OnShown
                                  ])
  : Modifier[Event => FXElement[_ <: JFXParent], Menu] =

    mod.mapApply(
      (native: Menu) =>
        OnShown(
          FXProperty(native.onShownProperty()),
          native.getParentPopup.getScene.getWindow
        )
    )
}
