package io.github.bertderbecker.scalapfui.javafx

import io.github.bertderbecker.scalapfui.javafx.property.FXProperty
import io.github.bertderbecker.scalapfui.property.{Property, ReadableProperty}
import org.scalatest._
import Implicits._

class PropertySpec extends FunSuite {

  test("Conditions binding") {
    import io.github.bertderbecker.scalapfui.javafx.property.Conditions._
    val boolean: Property[Boolean] = FXProperty(false)
    val result: ReadableProperty[Int] = when(boolean) choose 0 otherwise 1
    boolean.update(true)
    assert(result.value contains 0)
  }

  test("Addition of properties") {

    val summandor1: Property[Int] = FXProperty(1)
    val summandor2: Property[Int] = FXProperty(2)

    val sum1: Option[Int] = (summandor1 + summandor2).value
    assert(sum1 contains 3)

    val sumReadableProperty: Property[Int] = FXProperty(5)
    assert(sumReadableProperty.value contains 5)

    sumReadableProperty <== summandor1 + summandor2
    assert(sumReadableProperty.value contains 3)

    summandor1 update 5
    assert(sumReadableProperty.value contains 7)

  }

  test("Mapping of properties") {

    val intProp: Property[Int] = FXProperty(1)

    val sum1StringRepProp: ReadableProperty[String] = readablePropertyMonad.map(intProp)(_.toString)
    println(intProp.value)
    assert(sum1StringRepProp.value contains "1")

    intProp update 5
    assert(sum1StringRepProp.value contains "5")

  }

}
