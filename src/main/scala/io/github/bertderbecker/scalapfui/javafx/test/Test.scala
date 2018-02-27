package io.github.bertderbecker.scalapfui.javafx.test

import javafx.scene.layout.{Pane => JFXPane, VBox => JFXVBox}

import io.github.bertderbecker.scalapfui.attribute.StoredReadableAttribute
import io.github.bertderbecker.scalapfui.javafx.attribute.{FXStoredAttribute, FXStoredReadableAttribute}
import io.github.bertderbecker.scalapfui.javafx.event.EventReactor
import io.github.bertderbecker.scalapfui.javafx.event.EventReactor._
import io.github.bertderbecker.scalapfui.javafx.scene.SceneExts._
import io.github.bertderbecker.scalapfui.javafx.scene.control.MenuBarExts._
import io.github.bertderbecker.scalapfui.javafx.scene.control.MenuExts._
import io.github.bertderbecker.scalapfui.javafx.scene.control.MenuItemExts._
import io.github.bertderbecker.scalapfui.javafx.scene.control.TextFieldExts._
import io.github.bertderbecker.scalapfui.javafx.scene.layout.OrderedBoxes._
import io.github.bertderbecker.scalapfui.javafx.stage.StageExts._
import io.github.bertderbecker.scalapfui.javafx.{FXParent, JFXApp}

import scala.language.postfixOps


object Test extends JFXApp {

  val textFieldText: FXStoredAttribute[String] = FXStoredAttribute("enter")
  val stageWidth: StoredReadableAttribute[Double] = FXStoredReadableAttribute[Double]()
  val stageHeight: StoredReadableAttribute[Double] = FXStoredReadableAttribute[Double]()

  def buildLayout(x: Int): FXParent[JFXVBox] =
    VBox(
      MenuBar(
        Menu(
          MenuItem(
            menuItem.text := "" + x + " Actions",
            menuItem.onAction := rebuild(_ => buildLayout(x + 1))
          )
        )(
          menu.text := "Menu"
        )
      )(
        menuBar.prefWidth <== menuBar.layoutX
      ),
      TextField(
        textField.text := "" + x + " Actions",
        textField.text ==> textFieldText,
        textField.onAction := modify(textFieldText := "entered !!"),
        textField.onMouseReleased := EventReactor.rebuild(_ => buildLayout(x - 1))
      )
    )()

  primaryStage =
    Stage(
      stage.title := "IT WORKS!!!!!!!!!!!",
      stage.width ==> stageWidth,
      stage.height ==> stageHeight,
      stage.scene := Scene(
        buildLayout(0)
      )()
    )
}