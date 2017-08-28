# scalapfui-javafx
A work-in-progress implementation of scalapfui for JavaFX.

Here's an example:

```
package io.github.bertderbecker.scalapfui.javafx.test

import io.github.bertderbecker.scalapfui.javafx.scene.SceneExts._
import io.github.bertderbecker.scalapfui.javafx.scene.control.Actions._
import io.github.bertderbecker.scalapfui.javafx.scene.control.LabelExts._
import io.github.bertderbecker.scalapfui.javafx.scene.control.Text._
import io.github.bertderbecker.scalapfui.javafx.scene.control.TextFieldExts._
import io.github.bertderbecker.scalapfui.javafx.scene.layout.PaneExts._
import io.github.bertderbecker.scalapfui.javafx.stage.StageExts._
import io.github.bertderbecker.scalapfui.javafx.{FXParent, JFXApp}

import scala.language.postfixOps


object Test extends JFXApp {

  def buildLayout(x: Int): FXParent[JFXPane] =
    Pane()(
      Label(
        text := "Hello World!"
      )
      ,
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
```

Other examples are in the package io.github.bertderbecker.scalapfui.javafx.test.
