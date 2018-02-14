package io.github.bertderbecker.scalapfui.javafx.stage

import javafx.scene.{Scene, Parent => JFXParent}
import javafx.stage.{Window, WindowEvent}

import io.github.bertderbecker.scalapfui.attribute.{Attribute, ReadableAttribute}
import io.github.bertderbecker.scalapfui.javafx.Implicits._
import io.github.bertderbecker.scalapfui.javafx.attribute.{FXAttribute, FXReadableAttribute}
import io.github.bertderbecker.scalapfui.javafx.event.EventReactor

//Ready
object WindowExts {

  trait Attributes {

    val focused: ReadableAttribute[Boolean, Window] =
      FXReadableAttribute[java.lang.Boolean, Window](_.focusedProperty().asObject())

    val nativeScene: ReadableAttribute[Scene, Window] =
      FXReadableAttribute[Scene, Window](_.sceneProperty())

    val onCloseRequest: Attribute[EventReactor[WindowEvent], Window] =
      FXAttribute.forEventReactor(_.onCloseRequestProperty())

    val onHidden: Attribute[EventReactor[WindowEvent], Window] =
      FXAttribute.forEventReactor(_.onHiddenProperty())

    val onHiding: Attribute[EventReactor[WindowEvent], Window] =
      FXAttribute.forEventReactor(_.onHidingProperty())

    val onShowing: Attribute[EventReactor[WindowEvent], Window] =
      FXAttribute.forEventReactor(_.onShowingProperty())

    val onShown: Attribute[EventReactor[WindowEvent], Window] =
      FXAttribute.forEventReactor(_.onShownProperty())

    val opacity: Attribute[Double, Window] =
      FXAttribute[java.lang.Double, Window](_.opacityProperty().asObject())


    val showing: ReadableAttribute[Boolean, Window] =
      FXReadableAttribute[java.lang.Boolean, Window](_.showingProperty().asObject())

    val x: ReadableAttribute[Double, Window] =
      FXReadableAttribute[java.lang.Double, Window](_.xProperty().asObject())

    val y: ReadableAttribute[Double, Window] =
      FXReadableAttribute[java.lang.Double, Window](_.yProperty().asObject())
  }

  object window extends Attributes

}
