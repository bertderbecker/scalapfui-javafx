package io.github.bertderbecker.scalapfui.javafx.scene.shape

import javafx.scene.paint.Paint
import javafx.scene.shape.{Shape, StrokeLineCap => JFXStrokeLineCap, StrokeLineJoin => JFXStrokeLineJoin, StrokeType => JFXStrokeType}

import io.github.bertderbecker.scalapfui.Modifier
import io.github.bertderbecker.scalapfui.attribute.Attribute
import io.github.bertderbecker.scalapfui.javafx.Implicits._
import io.github.bertderbecker.scalapfui.javafx.attribute.FXAttribute
import io.github.bertderbecker.scalapfui.javafx.scene.NodeExts


//Ready
object ShapeExts {


  trait Attributes extends NodeExts.Attributes {

    val fill: Attribute[Paint, Shape] = FXAttribute[Paint, Shape](_.fillProperty())

    val smooth: Attribute[Boolean, Shape] =
      FXAttribute[java.lang.Boolean, Shape](_.smoothProperty().asObject())

    val strokeDashOffset: Attribute[Double, Shape] =
      FXAttribute[java.lang.Double, Shape](_.strokeDashOffsetProperty().asObject())

    val strokeLineCap: Attribute[JFXStrokeLineCap, Shape] =
      FXAttribute[JFXStrokeLineCap, Shape](_.strokeLineCapProperty())

    object StrokeLineCap {
      val Butt: Modifier[JFXStrokeLineCap, Shape] = strokeLineCap := JFXStrokeLineCap.BUTT
      val Round: Modifier[JFXStrokeLineCap, Shape] = strokeLineCap := JFXStrokeLineCap.ROUND
      val Square: Modifier[JFXStrokeLineCap, Shape] = strokeLineCap := JFXStrokeLineCap.SQUARE
    }

    val strokeLineJoin: Attribute[JFXStrokeLineJoin, Shape] =
      FXAttribute[JFXStrokeLineJoin, Shape](_.strokeLineJoinProperty())

    object StrokeLineJoin {
      val Bevel: Modifier[JFXStrokeLineJoin, Shape] = strokeLineJoin := JFXStrokeLineJoin.BEVEL
      val Miter: Modifier[JFXStrokeLineJoin, Shape] = strokeLineJoin := JFXStrokeLineJoin.MITER
      val Round: Modifier[JFXStrokeLineJoin, Shape] = strokeLineJoin := JFXStrokeLineJoin.ROUND
    }

    val strokeMiterLimit: Attribute[Double, Shape] =
      FXAttribute[java.lang.Double, Shape](_.strokeMiterLimitProperty().asObject())

    val stroke: Attribute[Paint, Shape] = FXAttribute[Paint, Shape](_.strokeProperty())

    val strokeType: Attribute[JFXStrokeType, Shape] =
      FXAttribute[JFXStrokeType, Shape](_.strokeTypeProperty())

    object StrokeType {
      val Centered: Modifier[JFXStrokeType, Shape] = strokeType := JFXStrokeType.CENTERED
      val Inside: Modifier[JFXStrokeType, Shape] = strokeType := JFXStrokeType.INSIDE
      val Outside: Modifier[JFXStrokeType, Shape] = strokeType := JFXStrokeType.OUTSIDE
    }

    val strokeWidth: Attribute[Double, Shape] =
      FXAttribute[java.lang.Double, Shape](_.strokeWidthProperty().asObject())
  }

  object shape extends Attributes

}
