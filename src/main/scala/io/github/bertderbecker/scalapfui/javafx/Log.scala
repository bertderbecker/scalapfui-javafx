package io.github.bertderbecker.scalapfui.javafx

object Log {

  var list: Seq[String] = Seq.empty

  def apply(text: String): Unit = {
    print(list.reverse.mkString)
    print(text + "    Level: " + list.size)
    print("\n")
  }

  def start(text: String): Unit = {
    if (list.nonEmpty) list = list.+:("|| ")
    apply(text)
    list = list.+:(" .. ")
  }

  def finish(text: String): Unit = {
    list = list.tail
    apply(text)
    if (list.nonEmpty) list = list.tail
  }
}
