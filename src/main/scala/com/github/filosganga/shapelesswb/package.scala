package com.github.filosganga

package object shapelesswb {

  val shapes: List[Shape] = List(Rectangle(3.0, 4.0), Circle(1.0))

  val trees: List[Tree[Shape]] = List(
    Branch(Leaf(Circle(1.0)), Branch(Leaf(Circle(2.0)), Leaf(Rectangle(1.2, 3.4)))),
    Leaf(Circle(6.7)),
    Branch(Branch(Leaf(Rectangle(9.0,4.5)), Branch(Leaf(Circle(3.2)), Leaf(Rectangle(8.4, 3.5)))), Leaf(Circle(5.2)))
  )

  def writeCsv[A](values: List[A])(implicit enc: CsvEncoder[A]): String =
    values.map(value => enc.encode(value).mkString(",")).mkString("\n")

}
