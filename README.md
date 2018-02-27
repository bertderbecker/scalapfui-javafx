# scalapfui-javafx
A work-in-progress implementation of scalapfui for JavaFX.

Here's an example:

```
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
      stage.title := "Hello World !!!",
      stage.width ==> stageWidth,
      stage.height ==> stageHeight,
      stage.scene := Scene(
        buildLayout(0)
      )()
    )
}
```
Another example is the [sample implementation of Connect4](https://github.com/bertderbecker/Connect4).