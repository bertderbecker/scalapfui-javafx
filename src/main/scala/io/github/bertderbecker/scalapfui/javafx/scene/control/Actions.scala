package io.github.bertderbecker.scalapfui.javafx.scene.control

import javafx.beans.property.ObjectProperty
import javafx.event.{ActionEvent, Event, EventHandler}
import javafx.scene.control.{MenuItem, TextField}
import javafx.scene.input._
import javafx.scene.{Node, Parent => JFXParent}
import javafx.stage.Window

import io.github.bertderbecker.scalapfui.Modifier
import io.github.bertderbecker.scalapfui.attribute.Attribute
import io.github.bertderbecker.scalapfui.javafx.Implicits._
import io.github.bertderbecker.scalapfui.javafx.FXElement
import io.github.bertderbecker.scalapfui.javafx.attribute.FXAttribute
import io.github.bertderbecker.scalapfui.javafx.property.FXProperty
import io.github.bertderbecker.scalapfui.property.Property

import scala.language.implicitConversions

object Actions {

  case class OnAction(property: Property[EventHandler[ActionEvent]], window: () => Window)

  val onAction: Attribute[ActionEvent => FXElement[_ <: JFXParent], OnAction] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onAction2TextFieldMod(
                                      mod: Modifier[ActionEvent => FXElement[_ <: JFXParent], OnAction]
                                    ): Modifier[(ActionEvent) => FXElement[_ <: JFXParent], TextField] =
    mod.mapApply(
      native => OnAction(
        FXProperty(native.onActionProperty()), {
          println("Getting the window...")
          lazy val w = native.getScene.getWindow
          println("I Have a Window")
          () => w
        }
      )
    )

  implicit def onAction2MenuItemMod(
                                     mod: Modifier[ActionEvent => FXElement[_ <: JFXParent], OnAction]
                                   ): Modifier[(ActionEvent) => FXElement[_ <: JFXParent], MenuItem] =
    mod.mapApply(//TODO: Make window in other actions lazy
      native => OnAction(FXProperty(native.onActionProperty()), () => native.getParentPopup.getScene.getWindow)
    )

  case class OnContextMenuRequested(
                                     property: Property[EventHandler[ContextMenuEvent]],
                                     window: () => Window
                                   )

  val onContextMenuRequested
  : Attribute[ContextMenuEvent => FXElement[_ <: JFXParent], OnContextMenuRequested] =
    FXAttribute.forEventHandler[ContextMenuEvent, OnContextMenuRequested](_.property, _.window())

  implicit def onContextMenuRequested2NodeMod(
                                               mod: Modifier[
                                                 ContextMenuEvent => FXElement[_ <: JFXParent],
                                                 OnContextMenuRequested
                                                 ])
  : Modifier[ContextMenuEvent => FXElement[_ <: JFXParent], Node] =
    mod.mapApply(
      (native: Node) =>
        OnContextMenuRequested(
          propertyInvariant.imap(
            FXProperty(native.onContextMenuRequestedProperty())
          )(_.asInstanceOf[EventHandler[ContextMenuEvent]])(_.asInstanceOf[EventHandler[_ >: ContextMenuEvent <: Event]]),
          () => native.getScene.getWindow
        )
    )


  /*
  case class OnDragDetected[T >: ContextMenuEvent](
                                                    property: ObjectProperty[EventHandler[T]],
                                                    window: () => Window
                                                  )

  val onDragDetected
  : Attribute[MouseEvent => FXElement[_ <: JFXParent], OnDragDetected[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onDragDetected2NodeMod(
                                       mod: Modifier[
                                         MouseEvent => FXElement[_ <: JFXParent],
                                         OnDragDetected[_]
                                         ])
  : Modifier[MouseEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnDragDetected(
          native.onDragDetectedProperty(),
          native.getScene.getWindow
        )
    )


  case class OnDragDone[T >: DragEvent](
                                         property: ObjectProperty[EventHandler[T]],
                                         window: () => Window
                                       )

  val onDragDone
  : Attribute[DragEvent => FXElement[_ <: JFXParent], OnDragDone[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onDragDone2NodeMod(
                                   mod: Modifier[
                                     DragEvent => FXElement[_ <: JFXParent],
                                     OnDragDone[_]
                                     ])
  : Modifier[DragEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnDragDone(
          native.onDragDoneProperty(),
          native.getScene.getWindow
        )
    )


  case class OnDragDropped[T >: DragEvent](
                                            property: ObjectProperty[EventHandler[T]],
                                            window: () => Window
                                          )

  val onDragDropped: Attribute[DragEvent => FXElement[_ <: JFXParent], OnDragDropped[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onDragDropped2NodeMod(
                                      mod: Modifier[
                                        DragEvent => FXElement[_ <: JFXParent],
                                        OnDragDropped[_]
                                        ])
  : Modifier[DragEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnDragDropped(
          native.onDragDroppedProperty(),
          native.getScene.getWindow
        )
    )


  case class OnDragEntered[T >: DragEvent](
                                            property: ObjectProperty[EventHandler[T]],
                                            window: () => Window
                                          )

  val onDragEntered: Attribute[DragEvent => FXElement[_ <: JFXParent], OnDragEntered[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onDragEntered2NodeMod(
                                      mod: Modifier[
                                        DragEvent => FXElement[_ <: JFXParent],
                                        OnDragEntered[_]
                                        ])
  : Modifier[DragEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnDragEntered(
          native.onDragEnteredProperty(),
          native.getScene.getWindow
        )
    )

  case class OnDragExited[T >: DragEvent](
                                            property: ObjectProperty[EventHandler[T]],
                                            window: () => Window
                                          )

  val onDragExited: Attribute[DragEvent => FXElement[_ <: JFXParent], OnDragExited[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onDragExited2NodeMod(
                                      mod: Modifier[
                                        DragEvent => FXElement[_ <: JFXParent],
                                        OnDragExited[_]
                                        ])
  : Modifier[DragEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnDragExited(
          native.onDragExitedProperty(),
          native.getScene.getWindow
        )
    )


  case class OnDragOver[T >: DragEvent](
                                           property: ObjectProperty[EventHandler[T]],
                                           window: () => Window
                                         )

  val onDragOver: Attribute[DragEvent => FXElement[_ <: JFXParent], OnDragOver[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onDragOver2NodeMod(
                                     mod: Modifier[
                                       DragEvent => FXElement[_ <: JFXParent],
                                       OnDragOver[_]
                                       ])
  : Modifier[DragEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnDragOver(
          native.onDragOverProperty(),
          native.getScene.getWindow
        )
    )



  case class OnInputMethodTextChanged[T >: InputMethodEvent](
                                           property: ObjectProperty[EventHandler[T]],
                                           window: () => Window
                                         )

  val onInputMethodTextChanged
  : Attribute[InputMethodEvent => FXElement[_ <: JFXParent], OnInputMethodTextChanged[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onInputMethodTextChanged2NodeMod(
                                                 mod: Modifier[
                                                   InputMethodEvent => FXElement[_ <: JFXParent],
                                                   OnInputMethodTextChanged[_]
                                                   ])
  : Modifier[InputMethodEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnInputMethodTextChanged(
          native.onInputMethodTextChangedProperty(),
          native.getScene.getWindow
        )
    )



  case class OnKeyPressed[T >: KeyEvent](
                                          property: ObjectProperty[EventHandler[T]],
                                          window: () => Window
                                        )

  val onKeyPressed: Attribute[KeyEvent => FXElement[_ <: JFXParent], OnKeyPressed[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onKeyPressed2NodeMod(
                                      mod: Modifier[
                                        KeyEvent => FXElement[_ <: JFXParent],
                                        OnKeyPressed[_]
                                        ])
  : Modifier[KeyEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnKeyPressed(
          native.onKeyPressedProperty(),
          native.getScene.getWindow
        )
    )


  case class OnKeyReleased[T >: KeyEvent](
                                          property: ObjectProperty[EventHandler[T]],
                                          window: () => Window
                                        )

  val onKeyReleased: Attribute[KeyEvent => FXElement[_ <: JFXParent], OnKeyReleased[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onKeyReleased2NodeMod(
                                      mod: Modifier[
                                        KeyEvent => FXElement[_ <: JFXParent],
                                        OnKeyReleased[_]
                                        ])
  : Modifier[KeyEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnKeyReleased(
          native.onKeyReleasedProperty(),
          native.getScene.getWindow
        )
    )


  case class OnKeyTyped[T >: KeyEvent](
                                          property: ObjectProperty[EventHandler[T]],
                                          window: () => Window
                                        )

  val onKeyTyped: Attribute[KeyEvent => FXElement[_ <: JFXParent], OnKeyTyped[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onKeyTyped2NodeMod(
                                      mod: Modifier[
                                        KeyEvent => FXElement[_ <: JFXParent],
                                        OnKeyTyped[_]
                                        ])
  : Modifier[KeyEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnKeyTyped(
          native.onKeyTypedProperty(),
          native.getScene.getWindow
        )
    )



  case class OnMouseClicked[T >: MouseEvent](
                                          property: ObjectProperty[EventHandler[T]],
                                          window: () => Window
                                        )

  val onMouseClicked: Attribute[MouseEvent => FXElement[_ <: JFXParent], OnMouseClicked[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onMouseClicked2NodeMod(
                                      mod: Modifier[
                                        MouseEvent => FXElement[_ <: JFXParent],
                                        OnMouseClicked[_]
                                        ])
  : Modifier[MouseEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnMouseClicked(
          native.onMouseClickedProperty(),
          native.getScene.getWindow
        )
    )



  case class OnMouseDragEntered[T >: MouseDragEvent](
                                        property: ObjectProperty[EventHandler[T]],
                                        window: () => Window
                                      )

  val onMouseDragEntered: Attribute[MouseDragEvent => FXElement[_ <: JFXParent], OnMouseDragEntered[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onMouseDragEntered2NodeMod(
                                   mod: Modifier[
                                     MouseDragEvent => FXElement[_ <: JFXParent],
                                     OnMouseDragEntered[_]
                                     ])
  : Modifier[MouseDragEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnMouseDragEntered(
          native.onMouseDragEnteredProperty(),
          native.getScene.getWindow
        )
    )


  case class OnMouseDragExited[T >: MouseDragEvent](
                                                      property: ObjectProperty[EventHandler[T]],
                                                      window: () => Window
                                                    )

  val onMouseDragExited: Attribute[MouseDragEvent => FXElement[_ <: JFXParent], OnMouseDragExited[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onMouseDragExited2NodeMod(
                                           mod: Modifier[
                                             MouseDragEvent => FXElement[_ <: JFXParent],
                                             OnMouseDragExited[_]
                                             ])
  : Modifier[MouseDragEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnMouseDragExited(
          native.onMouseDragExitedProperty(),
          native.getScene.getWindow
        )
    )


  case class OnMouseDragged[T >: MouseEvent](
                                                     property: ObjectProperty[EventHandler[T]],
                                                     window: () => Window
                                                   )

  val onMouseDragged: Attribute[MouseEvent => FXElement[_ <: JFXParent], OnMouseDragged[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onMouseDragged2NodeMod(
                                          mod: Modifier[
                                            MouseEvent => FXElement[_ <: JFXParent],
                                            OnMouseDragged[_]
                                            ])
  : Modifier[MouseEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnMouseDragged(
          native.onMouseDraggedProperty(),
          native.getScene.getWindow
        )
    )




  case class OnMouseDragOver[T >: MouseDragEvent](
                                                     property: ObjectProperty[EventHandler[T]],
                                                     window: () => Window
                                                   )

  val onMouseDragOver: Attribute[MouseDragEvent => FXElement[_ <: JFXParent], OnMouseDragOver[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onMouseDragOver2NodeMod(
                                          mod: Modifier[
                                            MouseDragEvent => FXElement[_ <: JFXParent],
                                            OnMouseDragOver[_]
                                            ])
  : Modifier[MouseDragEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnMouseDragOver(
          native.onMouseDragOverProperty(),
          native.getScene.getWindow
        )
    )



  case class OnMouseDragReleased[T >: MouseDragEvent](
                                                     property: ObjectProperty[EventHandler[T]],
                                                     window: () => Window
                                                   )

  val onMouseDragReleased: Attribute[MouseDragEvent => FXElement[_ <: JFXParent], OnMouseDragReleased[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onMouseDragReleased2NodeMod(
                                          mod: Modifier[
                                            MouseDragEvent => FXElement[_ <: JFXParent],
                                            OnMouseDragReleased[_]
                                            ])
  : Modifier[MouseDragEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnMouseDragReleased(
          native.onMouseDragReleasedProperty(),
          native.getScene.getWindow
        )
    )



  case class OnMouseEntered[T >: MouseEvent](
                                              property: ObjectProperty[EventHandler[T]],
                                              window: () => Window
                                            )

  val onMouseEntered: Attribute[MouseEvent => FXElement[_ <: JFXParent], OnMouseEntered[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onMouseEntered2NodeMod(
                                       mod: Modifier[
                                         MouseEvent => FXElement[_ <: JFXParent],
                                         OnMouseEntered[_]
                                         ])
  : Modifier[MouseEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnMouseEntered(
          native.onMouseEnteredProperty(),
          native.getScene.getWindow
        )
    )



  case class OnMouseExited[T >: MouseEvent](
                                              property: ObjectProperty[EventHandler[T]],
                                              window: () => Window
                                            )

  val onMouseExited: Attribute[MouseEvent => FXElement[_ <: JFXParent], OnMouseExited[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onMouseExited2NodeMod(
                                       mod: Modifier[
                                         MouseEvent => FXElement[_ <: JFXParent],
                                         OnMouseExited[_]
                                         ])
  : Modifier[MouseEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnMouseExited(
          native.onMouseExitedProperty(),
          native.getScene.getWindow
        )
    )



  case class OnMouseMoved[T >: MouseEvent](
                                              property: ObjectProperty[EventHandler[T]],
                                              window: () => Window
                                            )

  val onMouseMoved: Attribute[MouseEvent => FXElement[_ <: JFXParent], OnMouseMoved[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onMouseMoved2NodeMod(
                                       mod: Modifier[
                                         MouseEvent => FXElement[_ <: JFXParent],
                                         OnMouseMoved[_]
                                         ])
  : Modifier[MouseEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnMouseMoved(
          native.onMouseMovedProperty(),
          native.getScene.getWindow
        )
    )



  case class OnMousePressed[T >: MouseEvent](
                                              property: ObjectProperty[EventHandler[T]],
                                              window: () => Window
                                            )

  val onMousePressed: Attribute[MouseEvent => FXElement[_ <: JFXParent], OnMousePressed[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onMousePressed2NodeMod(
                                       mod: Modifier[
                                         MouseEvent => FXElement[_ <: JFXParent],
                                         OnMousePressed[_]
                                         ])
  : Modifier[MouseEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnMousePressed(
          native.onMousePressedProperty(),
          native.getScene.getWindow
        )
    )



  case class OnMouseReleased[T >: MouseEvent](
                                              property: ObjectProperty[EventHandler[T]],
                                              window: () => Window
                                            )

  val onMouseReleased: Attribute[MouseEvent => FXElement[_ <: JFXParent], OnMouseReleased[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onMouseReleased2NodeMod(
                                       mod: Modifier[
                                         MouseEvent => FXElement[_ <: JFXParent],
                                         OnMouseReleased[_]
                                         ])
  : Modifier[MouseEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnMouseReleased(
          native.onMouseReleasedProperty(),
          native.getScene.getWindow
        )
    )



  case class OnRotate[T >: RotateEvent](
                                              property: ObjectProperty[EventHandler[T]],
                                              window: () => Window
                                            )

  val onRotate: Attribute[RotateEvent => FXElement[_ <: JFXParent], OnRotate[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onRotate2NodeMod(
                                       mod: Modifier[
                                         RotateEvent => FXElement[_ <: JFXParent],
                                         OnRotate[_]
                                         ])
  : Modifier[RotateEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnRotate(
          native.onRotateProperty(),
          native.getScene.getWindow
        )
    )


  case class OnRotationFinished[T >: RotateEvent](
                                         property: ObjectProperty[EventHandler[T]],
                                         window: () => Window
                                       )

  val onRotationFinished: Attribute[RotateEvent => FXElement[_ <: JFXParent], OnRotationFinished[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onRotationFinished2NodeMod(
                                 mod: Modifier[
                                   RotateEvent => FXElement[_ <: JFXParent],
                                   OnRotationFinished[_]
                                   ])
  : Modifier[RotateEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnRotationFinished(
          native.onRotationFinishedProperty(),
          native.getScene.getWindow
        )
    )


  case class OnRotationStarted[T >: RotateEvent](
                                         property: ObjectProperty[EventHandler[T]],
                                         window: () => Window
                                       )

  val onRotationStarted: Attribute[RotateEvent => FXElement[_ <: JFXParent], OnRotationStarted[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onRotationStarted2NodeMod(
                                 mod: Modifier[
                                   RotateEvent => FXElement[_ <: JFXParent],
                                   OnRotationStarted[_]
                                   ])
  : Modifier[RotateEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnRotationStarted(
          native.onRotationStartedProperty(),
          native.getScene.getWindow
        )
    )


  case class OnScrollFinished[T >: ScrollEvent](
                                         property: ObjectProperty[EventHandler[T]],
                                         window: () => Window
                                       )

  val onScrollFinished: Attribute[ScrollEvent => FXElement[_ <: JFXParent], OnScrollFinished[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onScrollFinished2NodeMod(
                                 mod: Modifier[
                                   ScrollEvent => FXElement[_ <: JFXParent],
                                   OnScrollFinished[_]
                                   ])
  : Modifier[ScrollEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnScrollFinished(
          native.onScrollFinishedProperty(),
          native.getScene.getWindow
        )
    )


  case class OnScroll[T >: ScrollEvent](
                                                 property: ObjectProperty[EventHandler[T]],
                                                 window: () => Window
                                               )

  val onScroll: Attribute[ScrollEvent => FXElement[_ <: JFXParent], OnScroll[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onScroll2NodeMod(
                                         mod: Modifier[
                                           ScrollEvent => FXElement[_ <: JFXParent],
                                           OnScroll[_]
                                           ])
  : Modifier[ScrollEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnScroll(
          native.onScrollProperty(),
          native.getScene.getWindow
        )
    )


  case class OnScrollStarted[T >: ScrollEvent](
                                                 property: ObjectProperty[EventHandler[T]],
                                                 window: () => Window
                                               )

  val onScrollStarted: Attribute[ScrollEvent => FXElement[_ <: JFXParent], OnScrollStarted[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onScrollStarted2NodeMod(
                                         mod: Modifier[
                                           ScrollEvent => FXElement[_ <: JFXParent],
                                           OnScrollStarted[_]
                                           ])
  : Modifier[ScrollEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnScrollStarted(
          native.onScrollStartedProperty(),
          native.getScene.getWindow
        )
    )



  case class OnSwipeDown[T >: SwipeEvent](
                                                 property: ObjectProperty[EventHandler[T]],
                                                 window: () => Window
                                               )

  val onSwipeDown: Attribute[SwipeEvent => FXElement[_ <: JFXParent], OnSwipeDown[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onSwipeDown2NodeMod(
                                         mod: Modifier[
                                           SwipeEvent => FXElement[_ <: JFXParent],
                                           OnSwipeDown[_]
                                           ])
  : Modifier[SwipeEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnSwipeDown(
          native.onSwipeDownProperty(),
          native.getScene.getWindow
        )
    )


  case class OnSwipeLeft[T >: SwipeEvent](
                                           property: ObjectProperty[EventHandler[T]],
                                           window: () => Window
                                         )

  val onSwipeLeft: Attribute[SwipeEvent => FXElement[_ <: JFXParent], OnSwipeLeft[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onSwipeLeft2NodeMod(
                                    mod: Modifier[
                                      SwipeEvent => FXElement[_ <: JFXParent],
                                      OnSwipeLeft[_]
                                      ])
  : Modifier[SwipeEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnSwipeLeft(
          native.onSwipeLeftProperty(),
          native.getScene.getWindow
        )
    )



  case class OnSwipeRight[T >: SwipeEvent](
                                           property: ObjectProperty[EventHandler[T]],
                                           window: () => Window
                                         )

  val onSwipeRight: Attribute[SwipeEvent => FXElement[_ <: JFXParent], OnSwipeRight[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onSwipeRight2NodeMod(
                                    mod: Modifier[
                                      SwipeEvent => FXElement[_ <: JFXParent],
                                      OnSwipeRight[_]
                                      ])
  : Modifier[SwipeEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnSwipeRight(
          native.onSwipeRightProperty(),
          native.getScene.getWindow
        )
    )


  case class OnSwipeUp[T >: SwipeEvent](
                                           property: ObjectProperty[EventHandler[T]],
                                           window: () => Window
                                         )

  val onSwipeUp: Attribute[SwipeEvent => FXElement[_ <: JFXParent], OnSwipeUp[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onSwipeUp2NodeMod(
                                    mod: Modifier[
                                      SwipeEvent => FXElement[_ <: JFXParent],
                                      OnSwipeUp[_]
                                      ])
  : Modifier[SwipeEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnSwipeUp(
          native.onSwipeUpProperty(),
          native.getScene.getWindow
        )
    )


  case class OnTouchMoved[T >: TouchEvent](
                                           property: ObjectProperty[EventHandler[T]],
                                           window: () => Window
                                         )

  val onTouchMoved: Attribute[TouchEvent => FXElement[_ <: JFXParent], OnTouchMoved[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onTouchMoved2NodeMod(
                                    mod: Modifier[
                                      TouchEvent => FXElement[_ <: JFXParent],
                                      OnTouchMoved[_]
                                      ])
  : Modifier[TouchEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnTouchMoved(
          native.onTouchMovedProperty(),
          native.getScene.getWindow
        )
    )



  case class OnTouchPressed[T >: TouchEvent](
                                            property: ObjectProperty[EventHandler[T]],
                                            window: () => Window
                                          )

  val onTouchPressed: Attribute[TouchEvent => FXElement[_ <: JFXParent], OnTouchPressed[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onTouchPressed2NodeMod(
                                     mod: Modifier[
                                       TouchEvent => FXElement[_ <: JFXParent],
                                       OnTouchPressed[_]
                                       ])
  : Modifier[TouchEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnTouchPressed(
          native.onTouchPressedProperty(),
          native.getScene.getWindow
        )
    )



  case class OnTouchReleased[T >: TouchEvent](
                                              property: ObjectProperty[EventHandler[T]],
                                              window: () => Window
                                            )

  val onTouchReleased: Attribute[TouchEvent => FXElement[_ <: JFXParent], OnTouchReleased[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onTouchReleased2NodeMod(
                                       mod: Modifier[
                                         TouchEvent => FXElement[_ <: JFXParent],
                                         OnTouchReleased[_]
                                         ])
  : Modifier[TouchEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnTouchReleased(
          native.onTouchReleasedProperty(),
          native.getScene.getWindow
        )
    )



  case class OnTouchStationary[T >: TouchEvent](
                                              property: ObjectProperty[EventHandler[T]],
                                              window: () => Window
                                            )

  val onTouchStationary: Attribute[TouchEvent => FXElement[_ <: JFXParent], OnTouchStationary[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onTouchStationary2NodeMod(
                                       mod: Modifier[
                                         TouchEvent => FXElement[_ <: JFXParent],
                                         OnTouchStationary[_]
                                         ])
  : Modifier[TouchEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnTouchStationary(
          native.onTouchStationaryProperty(),
          native.getScene.getWindow
        )
    )



  case class OnZoomFinished[T >: ZoomEvent](
                                              property: ObjectProperty[EventHandler[T]],
                                              window: () => Window
                                            )

  val onZoomFinished: Attribute[ZoomEvent => FXElement[_ <: JFXParent], OnZoomFinished[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onZoomFinished2NodeMod(
                                       mod: Modifier[
                                         ZoomEvent => FXElement[_ <: JFXParent],
                                         OnZoomFinished[_]
                                         ])
  : Modifier[ZoomEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnZoomFinished(
          native.onZoomFinishedProperty(),
          native.getScene.getWindow
        )
    )



  case class OnZoom[T >: ZoomEvent](
                                             property: ObjectProperty[EventHandler[T]],
                                             window: () => Window
                                           )

  val onZoom: Attribute[ZoomEvent => FXElement[_ <: JFXParent], OnZoom[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onZoom2NodeMod(
                                       mod: Modifier[
                                         ZoomEvent => FXElement[_ <: JFXParent],
                                         OnZoom[_]
                                         ])
  : Modifier[ZoomEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnZoom(
          native.onZoomProperty(),
          native.getScene.getWindow
        )
    )




  case class OnZoomStarted[T >: ZoomEvent](
                                             property: ObjectProperty[EventHandler[T]],
                                             window: () => Window
                                           )

  val onZoomStarted: Attribute[ZoomEvent => FXElement[_ <: JFXParent], OnZoomStarted[_]] =
    FXAttribute.forEventHandler(_.property, _.window())

  implicit def onZoomStarted2NodeMod(
                                       mod: Modifier[
                                         ZoomEvent => FXElement[_ <: JFXParent],
                                         OnZoomStarted[_]
                                         ])
  : Modifier[ZoomEvent => FXElement[_ <: JFXParent], Node] =

    mod.mapApply(
      (native: Node) =>
        OnZoomStarted(
          native.onZoomStartedProperty(),
          native.getScene.getWindow
        )
    )
*/
}
