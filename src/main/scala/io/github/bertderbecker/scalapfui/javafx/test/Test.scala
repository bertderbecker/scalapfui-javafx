package io.github.bertderbecker.scalapfui.javafx.test

import javafx.scene.layout.{Pane => JFXPane}
import javafx.scene.layout.{VBox => JFXVBox}

import io.github.bertderbecker.scalapfui.javafx.scene.SceneExts._
import io.github.bertderbecker.scalapfui.javafx.scene.layout.OrderedBoxes._
import io.github.bertderbecker.scalapfui.javafx.scene.layout.PaneExts._
import io.github.bertderbecker.scalapfui.javafx.stage.StageExts._
import io.github.bertderbecker.scalapfui.javafx.scene.control.MenuBarExts._
import io.github.bertderbecker.scalapfui.javafx.scene.control.MenuItemExts._
import io.github.bertderbecker.scalapfui.javafx.{FXParent, JFXApp}
import io.github.bertderbecker.scalapfui.javafx.scene.control.TextFieldExts._
import io.github.bertderbecker.scalapfui.javafx.scene.control.MenuExts._

import scala.language.postfixOps


object Test extends JFXApp {

  /*

  def test(): Unit = {

    implicit val intAddable: Addable[Int] = Addable(_ + _)

    val summandor1: Property[Int] = FXProperty(1)
    val summandor2: Property[Int] = FXProperty(2)
    val sumProperty = new SimpleIntegerProperty(-1)

    println("summandor1.value = " + summandor1.value)
    println("summandor2.value = " + summandor2.value)

    val sumReadableProperty: Property[Int] = FXProperty(5)

    println("(summandor1 + summandor2).value = " + summandor1.+(summandor2).value)

    val nativeSumProp: ReadableProperty[Int] = summandor1.+(summandor2)

    sumReadableProperty.bindTo(nativeSumProp)

    println("sumReadableProperty.value = " + sumReadableProperty.value)

    val sumStringProperty: ReadableProperty[String] =
      readablePropertyMonad.map(sumReadableProperty)(_.toString)

    summandor1.onChange(newVal => println("summandor1 changed!!! New Value = " + newVal))
    nativeSumProp.onChange(newVal => println("nativeSumProp changed!!! New Value = " + newVal))

    summandor1() = 5
    println("summandor1.value updated = " + summandor1.value)

    println("Result: " + sumStringProperty.value.toString)


    println("Start Test")

    test()


    implicit val stringAddable: Addable[String] =
      Addable[String] { (s1, s2) =>
        println("Combining String \"" + s1 + "\" and \"" + s2 + "\"")
        s1 + s2
      }

    println("Build property")

    val readableTextProperty = new SimpleStringProperty("A")
    val readableTextProperty2 =
      FXReadableProperty(new SimpleStringProperty("C"))


    println("Build ui")

    val ui: FXParent[javafx.scene.layout.Pane] =

      Pane()(
        Label(
          text := "Hello World!"
        ) labelFor
        TextField(
          text := "Entered " + 5 + " times"
          //, onAction := (_ => buildLayout(x + 1))
          //, onContextMenuRequested := (_ => buildLayout(x - 1))
        )
      )

    println("Render ui")

    val p: javafx.scene.layout.Pane = ui.render

    readableTextProperty.set("123456")

    println("Text of Label: " + p.getChildren.get(0).asInstanceOf[javafx.scene.control.Label].getText)

    println("Rendered children: " + p.getChildren)
    println("First child: " + p.getChildren.get(0))


    println("Finished Test")
  }

  */


  def buildLayout(x: Int): FXParent[JFXVBox] =
    VBox(
      MenuBar(
        Menu(
          MenuItem(
            menuItem.text := "" + x + " Actions",
            menuItem.onAction := (_ => buildLayout(x + 1))
          )
        )(
          menu.text := "Menu"
        )
      )(),
      TextField(
        textField.text := "" + x + " Actions",
        textField.onAction := (_ => buildLayout(x + 1))
        //, textField.onContextMenuRequested := (_ => buildLayout(x - 1))
      )
    )()

  primaryStage =
    Stage(
      title := "IT WORKS!!!!!!!!!!!",
      scene := Scene(
        buildLayout(0)
      )()
    )
}