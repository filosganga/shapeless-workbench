package com.github.filosganga

import shapeless._
import shapeless.ops.hlist._

package object shapelesswb {

  val ids = List(
    Id("IC-1"),
    Id("EM-4"),
    Id("SH-400")
  )

  val iceCreams: List[IceCream] = List(
    IceCream("Cornetto", 2, true),
    IceCream("Coppa Del Nonno", 1, false)
  )

  val shapes: List[Shape] = List(Rectangle(3.0, 4.0), Circle(1.0))

  val trees: List[Tree[Shape]] = List(
    Branch(Leaf(Circle(1.0)), Branch(Leaf(Circle(2.0)), Leaf(Rectangle(1.2, 3.4)))),
    Leaf(Circle(6.7)),
    Branch(Branch(Leaf(Rectangle(9.0, 4.5)), Branch(Leaf(Circle(3.2)), Leaf(Rectangle(8.4, 3.5)))), Leaf(Circle(5.2)))
  )

  def writeCsv[A](values: List[A])(implicit enc: CsvEncoder[A]): String =
    values.map(value => enc.encode(value).mkString(",")).mkString("\n")

  def getRepr[A](value: A)(implicit gen: Generic[A]) = gen.to(value)

  def secondField[A, R <: HList](input: A)(implicit gen: Generic.Aux[A, R], second: Second[R]): second.Out = second(gen.to(input))

  def lastField[A, R <: HList](input: A)(implicit gen: Generic.Aux[A, R], last: Last[R]): last.Out = last(gen.to(input))

  def getWrappedValue[A, R <: HList, H, T <: HList](input: A)(implicit gen: Generic.Aux[A, R], eisHCons: IsHCons. Aux [R, H, HNil]): H = gen.to(input).head
}
