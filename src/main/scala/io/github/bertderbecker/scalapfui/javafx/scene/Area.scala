package io.github.bertderbecker.scalapfui.javafx.scene

import io.github.bertderbecker.scalapfui.attribute.{Attribute, ReadableAttribute}
import javafx.beans.property.{DoubleProperty, ReadOnlyDoubleProperty, Property => JFXProperty}

import io.github.bertderbecker.scalapfui.javafx.Implicits._
import javafx.scene.layout.Region
import javafx.stage.Stage

import io.github.bertderbecker.scalapfui.Modifier
import io.github.bertderbecker.scalapfui.javafx.attribute.{FXAttribute, FXReadableAttribute}

import scala.language.implicitConversions

object Area {

  case class Height(property: ReadOnlyDoubleProperty)

  val height: ReadableAttribute[Double, Height] =
    FXReadableAttribute[java.lang.Double, Height](_.property.asObject())

  implicit def height2Region[T <: Region](
                                           attr: ReadableAttribute[Double, Height]
                                         ): ReadableAttribute[Double, T] =
    FXReadableAttribute[java.lang.Double, T](t => t.heightProperty().asObject())

  implicit def height2Stage[T <: Stage](
                                           attr: ReadableAttribute[Double, Height]
                                         ): ReadableAttribute[Double, T] =
    FXReadableAttribute[java.lang.Double, T](t => t.heightProperty().asObject())


  case class Width(property: ReadOnlyDoubleProperty)

  val width: ReadableAttribute[Double, Width] =
    FXReadableAttribute[java.lang.Double, Width](_.property.asObject())

  implicit def width2Region[T <: Region](
                                           attr: ReadableAttribute[Double, Width]
                                         ): ReadableAttribute[Double, T] =
    FXReadableAttribute[java.lang.Double, T](t => t.widthProperty().asObject())

  implicit def width2Stage[T <: Stage](
                                         attr: ReadableAttribute[Double, Width]
                                       ): ReadableAttribute[Double, T] =
    FXReadableAttribute[java.lang.Double, T](t => t.widthProperty().asObject())



  case class MaxHeight(property: DoubleProperty)

  val maxHeight: Attribute[Double, MaxHeight] =
    FXAttribute[java.lang.Double, MaxHeight](_.property.asObject())

  implicit def maxHeight2Region[T <: Region](
                                           mod: Modifier[Double, MaxHeight]
                                         ): Modifier[Double, T] =
    mod.mapApply[T](native => MaxHeight(native.maxHeightProperty()))

  implicit def maxHeight2Stage[T <: Stage](
                                         mod: Modifier[Double, MaxHeight]
                                       ): Modifier[Double, T] =
    mod.mapApply[T](native => MaxHeight(native.maxHeightProperty()))


  case class MaxWidth(property: DoubleProperty)

  val maxWidth: Attribute[Double, MaxWidth] =
    FXAttribute[java.lang.Double, MaxWidth](_.property.asObject())

  implicit def maxWidth2Region[T <: Region](
                                              mod: Modifier[Double, MaxWidth]
                                            ): Modifier[Double, T] =
    mod.mapApply[T](native => MaxWidth(native.maxWidthProperty()))

  implicit def maxWidth2Stage[T <: Stage](
                                            mod: Modifier[Double, MaxWidth]
                                          ): Modifier[Double, T] =
    mod.mapApply[T](native => MaxWidth(native.maxWidthProperty()))



  case class PrefHeight(property: DoubleProperty)

  val prefHeight: Attribute[Double, PrefHeight] =
    FXAttribute[java.lang.Double, PrefHeight](_.property.asObject())

  implicit def prefHeight2Region[T <: Region](
                                              mod: Modifier[Double, PrefHeight]
                                            ): Modifier[Double, T] =
    mod.mapApply[T](native => PrefHeight(native.prefHeightProperty()))


  case class PrefWidth(property: DoubleProperty)

  val prefWidth: Attribute[Double, PrefWidth] =
    FXAttribute[java.lang.Double, PrefWidth](_.property.asObject())

  implicit def prefWidth2Region[T <: Region](
                                             mod: Modifier[Double, PrefWidth]
                                           ): Modifier[Double, T] =
    mod.mapApply[T](native => PrefWidth(native.prefWidthProperty()))

}
