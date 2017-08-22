package io.github.bertderbecker.scalapfui.javafx.test

import javafx.beans.property.{SimpleIntegerProperty, SimpleStringProperty}
import javafx.scene.{Scene => JFXScene}
import javafx.scene.layout.{Pane => JFXPane}
import javafx.scene.control.{Label => JFXLabel}

import io.github.bertderbecker.scalapfui.Math.Addable
import io.github.bertderbecker.scalapfui.javafx.attribute.FXReadableAttribute
import io.github.bertderbecker.scalapfui.javafx.{FXParent, JFXApp, Log}
import io.github.bertderbecker.scalapfui.javafx.property.{FXProperty, FXReadableProperty}
import io.github.bertderbecker.scalapfui.property.{Property, ReadableProperty}
import io.github.bertderbecker.scalapfui.javafx.Implicits._
import io.github.bertderbecker.scalapfui.javafx.scene.layout.PaneExts._
import io.github.bertderbecker.scalapfui.javafx.scene.control.LabelExts._
import io.github.bertderbecker.scalapfui.javafx.scene.control.Text
import Text._
import io.github.bertderbecker.scalapfui.javafx.stage.StageExts._
import io.github.bertderbecker.scalapfui.javafx.scene.control.TextFieldExts._
import io.github.bertderbecker.scalapfui.javafx.scene.SceneExts._

import scala.language.postfixOps


object Test extends JFXApp {

  def test(): Unit = {

    implicit val intAddable: Addable[Int] = Addable(_ + _)

    val summandor1: Property[Int] = FXProperty(1)
    val summandor2: Property[Int] = FXProperty(2)
    val sumProperty = new SimpleIntegerProperty(-1)

    Log("summandor1.value = " + summandor1.value)
    Log("summandor2.value = " + summandor2.value)

    val sumReadableProperty: Property[Int] = FXProperty(5)

    Log("(summandor1 + summandor2).value = " + summandor1.+(summandor2).value)

    val nativeSumProp: ReadableProperty[Int] = summandor1.+(summandor2)

    sumReadableProperty.bindTo(nativeSumProp)

    Log("sumReadableProperty.value = " + sumReadableProperty.value)

    val sumStringProperty: ReadableProperty[String] =
      readablePropertyMonad.map(sumReadableProperty)(_.toString)

    summandor1.onChange(newVal => Log("summandor1 changed!!! New Value = " + newVal))
    nativeSumProp.onChange(newVal => Log("nativeSumProp changed!!! New Value = " + newVal))

    summandor1() = 5
    Log("summandor1.value updated = " + summandor1.value)

    Log("Result: " + sumStringProperty.value.toString)


    Log.start("Start Test")

    test()


    implicit val stringAddable: Addable[String] =
      Addable[String] { (s1, s2) =>
        Log("Combining String \"" + s1 + "\" and \"" + s2 + "\"")
        s1 + s2
      }

    Log("Build property")

    val readableTextProperty = new SimpleStringProperty("A")
    val readableTextProperty2 =
      FXReadableProperty(new SimpleStringProperty("C"))


    Log("Build ui")

    val ui: FXParent[javafx.scene.layout.Pane] =

      Pane()(
        Label(
          text <== FXReadableAttribute((_: Text) => readableTextProperty)
            .+("B")
            .+(readableTextProperty2)(stringAddable, readableAttributeFlatMap)
        ) labelFor TextField(text := ""))

    Log("Render ui")

    val p: javafx.scene.layout.Pane = ui.render

    readableTextProperty.set("123456")

    Log("Text of Label: " + p.getChildren.get(0).asInstanceOf[javafx.scene.control.Label].getText)

    Log("Rendered children: " + p.getChildren)
    Log("First child: " + p.getChildren.get(0))


    Log.finish("Finished Test")
  }

  def buildLayout(x: Int): FXParent[JFXPane] = Pane()(
    Label(
      text := "Hello World!"
    ),
    TextField(
      text := "Entered " + x + " times",
      onAction := (_ => buildLayout(x + 1))
    )
  )

  primaryStage =
    Stage(
      title := "IT WORKS!!!!!!!!!!!",
      scene := Scene(
        buildLayout(0)
      )()
    )
}