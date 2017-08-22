package io.github.bertderbecker.scalapfui.javafx.scene

import javafx.scene.{Scene, Parent => JFXParent}

import io.github.bertderbecker.scalapfui.javafx.{FXElementTag, FXParent}

//TODO: implement attributes
object SceneExts {

  val Scene: (FXParent[_ <: JFXParent]) => FXElementTag[Scene] =
    parent => FXElementTag(() => new Scene(parent.render))


}
