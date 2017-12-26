# scalapfui-javafx
A work-in-progress implementation of scalapfui for JavaFX.

Here's an example:

```package io.github.bertderbecker.scalapfui.javafx.test

   import javafx.beans.property.{SimpleIntegerProperty, SimpleStringProperty}
   import javafx.scene.layout.{Pane => JFXPane}
   import javafx.scene.layout.{VBox => JFXVBox}

   import io.github.bertderbecker.scalapfui.Math.Addable
   import io.github.bertderbecker.scalapfui.javafx.property.{FXProperty, FXReadableProperty}
   import io.github.bertderbecker.scalapfui.javafx.scene.SceneExts._
   import io.github.bertderbecker.scalapfui.javafx.scene.layout.OrderedBoxes._
   import io.github.bertderbecker.scalapfui.javafx.scene.layout.PaneExts._
   import io.github.bertderbecker.scalapfui.javafx.stage.StageExts._
   import io.github.bertderbecker.scalapfui.javafx.event.EventReactor._
   import io.github.bertderbecker.scalapfui.javafx.scene.control.MenuBarExts._
   import io.github.bertderbecker.scalapfui.javafx.scene.control.MenuItemExts._
   import io.github.bertderbecker.scalapfui.javafx.{FXParent, JFXApp}
   import io.github.bertderbecker.scalapfui.javafx.scene.control.TextFieldExts._
   import io.github.bertderbecker.scalapfui.javafx.scene.control.LabelExts._
   import io.github.bertderbecker.scalapfui.javafx.Implicits._
   import io.github.bertderbecker.scalapfui.javafx.attribute.FXStoredAttribute
   import io.github.bertderbecker.scalapfui.javafx.scene.control.MenuExts._
   import io.github.bertderbecker.scalapfui.property.{Property, ReadableProperty}

   import scala.language.postfixOps


   object Test extends JFXApp {

     val textFieldText: FXStoredAttribute[String] = FXStoredAttribute("0 Actions")

     def buildLayout(x: Int): FXParent[JFXVBox] = {
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
         )(),
         TextField(
           //textField.text := "" + x + " Actions",
           textField.text ==> textFieldText,
           textField.onAction := modify(textFieldText := "entered !!")
           //, textField.onContextMenuRequested := (_ => buildLayout(x - 1))
         )
       )()
     }

     primaryStage =
       Stage(
         title := "IT WORKS!!!!!!!!!!!",
         scene := Scene(
           buildLayout(0)
         )()
       )
   }
```

Other examples are in the package io.github.bertderbecker.scalapfui.javafx.test.
