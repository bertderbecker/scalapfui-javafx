package io.github.bertderbecker.scalapfui.javafx

import javafx.application.Application
import javafx.stage.Stage

import scala.collection.mutable.ListBuffer

trait JFXApp {

  def primaryStage: FXElement[Stage] = JFXApp.wrappedStage() //NOT USE!!!!

  def primaryStage_=(newStage: => FXElement[Stage]): Unit =
    JFXApp.wrappedStage = () => newStage

  private val subClassInitCode = new ListBuffer[() => Unit]

  def delayedInit(x: => Unit): Unit = {
    subClassInitCode += (() => x)
  }

  def main(args: Array[String]): Unit = {
    //println("Main")
    JFXApp.ActiveApp = this
    Application.launch(classOf[JFXApp.AppHelper], args: _*)
  }

  final def init(): Unit =
    for (initCode <- subClassInitCode) initCode()

  def stopApp(): Unit = {
    System.exit(0)
  }


}

object JFXApp {


  class AppHelper extends javafx.application.Application {
    def start(stage: javafx.stage.Stage) {
      JFXApp.ActiveJFXApp = this
      JFXApp.Stage = JFXApp.getUserDefinedStage(stage)
      JFXApp.ActiveApp.init()
      //println("Scene root: " + JFXApp.Stage.getScene.getRoot)
      //println("Scene root children: " + JFXApp.Stage.getScene.getRoot.getChildrenUnmodifiable)
      if (JFXApp.AutoShow) {
        JFXApp.Stage.show()
      }
    }

    override def stop() {
      JFXApp.ActiveApp.stopApp()
    }
  }

  var Stage: Stage = _

  var wrappedStage: () => FXElement[Stage] = _

  def getUserDefinedStage(primaryStage: Stage): Stage =
    wrappedStage().withChangedPure(primaryStage).render


  var ActiveApp: JFXApp = _

  var ActiveJFXApp: Application = _

  var AutoShow: Boolean = true

  /**
    * Regular expression for parsing name/value parameters.
    */
  private val keyValue =
    """^--([A-Za-z_][^=]*?)=(.*)$""".r
}
