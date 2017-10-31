package io.github.bertderbecker.scalapfui.javafx.scene

import javafx.geometry.{Bounds, NodeOrientation, Point3D}
import javafx.scene.effect.{BlendMode, Effect}
import javafx.scene._
import javafx.scene.input.{ContextMenuEvent, InputMethodRequests}
import javafx.scene.{Node, Parent => JFXParent}
import javafx.scene.transform.Transform

import io.github.bertderbecker.scalapfui.javafx.Implicits._
import io.github.bertderbecker.scalapfui.attribute.{Attribute, ReadableAttribute}
import io.github.bertderbecker.scalapfui.javafx.FXElement
import io.github.bertderbecker.scalapfui.javafx.attribute.{FXAttribute, FXReadableAttribute}
import io.github.bertderbecker.scalapfui.javafx.property.FXProperty
import io.github.bertderbecker.scalapfui.javafx.scene.control.TextFieldExts

import scala.language.implicitConversions

//Ready
object NodeExts {

  val accessibleHelp: Attribute[String, Node] =
    FXAttribute[String, Node](_.accessibleHelpProperty())

  val accessibleRoleDescription: Attribute[String, Node] =
    FXAttribute[String, Node](_.accessibleRoleDescriptionProperty())

  //TODO: support accessible roles
  val accessibleRole: Attribute[AccessibleRole, Node] =
    FXAttribute[AccessibleRole, Node](_.accessibleRoleProperty())

  val accessibleText: Attribute[String, Node] =
    FXAttribute[String, Node](_.accessibleTextProperty())

  val blendMode: Attribute[BlendMode, Node] =
    FXAttribute[BlendMode, Node](_.blendModeProperty())

  //TODO: support bounds
  val boundsInLocal: ReadableAttribute[Bounds, Node] =
    FXReadableAttribute[Bounds, Node](_.boundsInLocalProperty())

  val boundsInParent: ReadableAttribute[Bounds, Node] =
    FXReadableAttribute[Bounds, Node](_.boundsInParentProperty())

  val cacheHint: Attribute[CacheHint, Node] =
    FXAttribute[CacheHint, Node](_.cacheHintProperty())

  val cache: Attribute[Boolean, Node] =
    FXAttribute[java.lang.Boolean, Node](_.cacheProperty())

  val clip: Attribute[FXElement[_ <: Node], Node] =
    FXAttribute.fromProperty[FXElement[_ <: Node], Node](native =>
      propertyInvariant
        .imap(
          FXProperty[Node](native.clipProperty())
        )(
          n => FXElement.wrap(n).asInstanceOf[FXElement[_ <: Node]]
        )((_: FXElement[_ <: Node]).render))

  //TODO: support cursor
  val cursor: Attribute[Cursor, Node] =
    FXAttribute[Cursor, Node](_.cursorProperty())

  val depthTest: Attribute[DepthTest, Node] =
    FXAttribute[DepthTest, Node](_.depthTestProperty())

  val disabled: ReadableAttribute[Boolean, Node] =
    FXReadableAttribute[java.lang.Boolean, Node](_.disabledProperty())

  val disable: Attribute[Boolean, Node] =
    FXAttribute[java.lang.Boolean, Node](_.disableProperty())

  val effectiveNodeOrientation: ReadableAttribute[NodeOrientation, Node] =
    FXReadableAttribute[NodeOrientation, Node](_.effectiveNodeOrientationProperty())

  val effect: Attribute[Effect, Node] =
    FXAttribute[Effect, Node](_.effectProperty())

  val focused: ReadableAttribute[Boolean, Node] =
    FXReadableAttribute[java.lang.Boolean, Node](_.focusedProperty())

  val focusTraversable: Attribute[Boolean, Node] =
    FXAttribute[java.lang.Boolean, Node](_.focusTraversableProperty())

  val hover: ReadableAttribute[Boolean, Node] =
    FXReadableAttribute[java.lang.Boolean, Node](_.hoverProperty())

  val id: Attribute[String, Node] =
    FXAttribute[String, Node](_.idProperty())

  val inputMethodRequests: Attribute[InputMethodRequests, Node] =
    FXAttribute[InputMethodRequests, Node](_.inputMethodRequestsProperty())

  val layoutBounds: ReadableAttribute[Bounds, Node] =
    FXReadableAttribute[Bounds, Node](_.layoutBoundsProperty())

  val layoutX: Attribute[Double, Node] =
    FXAttribute[java.lang.Double, Node](_.layoutXProperty().asObject())

  val layoutY: Attribute[Double, Node] =
    FXAttribute[java.lang.Double, Node](_.layoutYProperty().asObject())

  val localToParentTransform: ReadableAttribute[Transform, Node] =
    FXReadableAttribute[Transform, Node](_.localToParentTransformProperty())

  val localToSceneTransform: ReadableAttribute[Transform, Node] =
    FXReadableAttribute[Transform, Node](_.localToSceneTransformProperty())

  val managed: Attribute[Boolean, Node] =
    FXAttribute[java.lang.Boolean, Node](_.managedProperty().asObject())

  val mouseTransparent: Attribute[Boolean, Node] =
    FXAttribute[java.lang.Boolean, Node](_.mouseTransparentProperty().asObject())

  val nodeOrientation: ReadableAttribute[NodeOrientation, Node] =
    FXReadableAttribute[NodeOrientation, Node](_.nodeOrientationProperty())

  val parent: ReadableAttribute[Parent, Node] =
    FXReadableAttribute[Parent, Node](_.parentProperty())

  val pickOnBounds: Attribute[Boolean, Node] =
    FXAttribute[java.lang.Boolean, Node](_.pickOnBoundsProperty().asObject())

  val pressed: ReadableAttribute[Boolean, Node] =
    FXReadableAttribute[java.lang.Boolean, Node](_.pressedProperty().asObject())

  val rotate: Attribute[Double, Node] =
    FXAttribute[java.lang.Double, Node](_.rotateProperty().asObject())

  val rotationAxis: Attribute[Point3D, Node] =
    FXAttribute[Point3D, Node](_.rotationAxisProperty())

  val scaleX: Attribute[Double, Node] =
    FXAttribute[java.lang.Double, Node](_.scaleXProperty().asObject())

  val scaleY: Attribute[Double, Node] =
    FXAttribute[java.lang.Double, Node](_.scaleYProperty().asObject())

  val scaleZ: Attribute[Double, Node] =
    FXAttribute[java.lang.Double, Node](_.scaleZProperty().asObject())

  //TODO: Is a ReadableAttribute "Scene" necessary?

  //Style is ready

  val translateX: Attribute[Double, Node] =
    FXAttribute[java.lang.Double, Node](_.translateXProperty().asObject())

  val translateY: Attribute[Double, Node] =
    FXAttribute[java.lang.Double, Node](_.translateYProperty().asObject())

  val translateZ: Attribute[Double, Node] =
    FXAttribute[java.lang.Double, Node](_.translateZProperty().asObject())

  val visible: Attribute[Boolean, Node] =
    FXAttribute[java.lang.Boolean, Node](_.visibleProperty().asObject())


  //TODO: support actions

  trait SharedAttributes {
    lazy val onContextMenuRequested
    : Attribute[ContextMenuEvent => FXElement[_ <: JFXParent], Node] =
      FXAttribute.forEventHandler[ContextMenuEvent, Node](_.onContextMenuRequestedProperty())

    val style: Attribute[String, Node] = FXAttribute[String, Node](_.styleProperty())
  }

  object node extends SharedAttributes
}