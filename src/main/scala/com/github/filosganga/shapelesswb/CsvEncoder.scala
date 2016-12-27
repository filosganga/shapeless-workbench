package com.github.filosganga.shapelesswb

import shapeless._

trait CsvEncoder[A] {
  def encode(value: A): List[String]
}

object CsvEncoder {

  def apply[A](implicit enc: CsvEncoder[A]): CsvEncoder[A] = enc

  def instance[A](func: A => List[String]): CsvEncoder[A] = (value: A) => func(value)

  implicit val booleanEncoder: CsvEncoder[Boolean] =
    instance(b => if (b) List("yes") else List("no"))

  implicit val longEncoder: CsvEncoder[Long] =
    instance(l => List(l.toString))

  implicit val intEncoder: CsvEncoder[Int] =
    instance(i => List(i.toString))

  implicit val doubleEncoder: CsvEncoder[Double] =
    instance(d => List(d.toString))

  implicit val floatEncoder: CsvEncoder[Float] =
    instance(f => List(f.toString))

  implicit val stringEncoder: CsvEncoder[String] =
    instance(s => List(s))

  implicit val hNilEncoder: CsvEncoder[HNil] =
    instance(hnil => Nil)

  implicit def hListEncoder[H, T <: HList](implicit hEncoder: Lazy[CsvEncoder[H]], tEncoder: CsvEncoder[T]): CsvEncoder[H :: T] =
    instance {
      case h :: t => hEncoder.value.encode(h) ++ tEncoder.encode(t)
    }

  implicit def genericEncoder[A, R](implicit gen: Generic.Aux[A, R], enc: Lazy[CsvEncoder[R]]): CsvEncoder[A] =
    instance(a => enc.value.encode(gen.to(a)))

  implicit val cNilEncoder: CsvEncoder[CNil] = instance(_ => throw new UnsupportedOperationException)

  implicit def coproductEncoder[H, T <: Coproduct](implicit hEncoder: Lazy[CsvEncoder[H]], tEncoder: CsvEncoder[T]): CsvEncoder[H :+: T] =
    instance{
      case Inl(h) => hEncoder.value.encode(h)
      case Inr(t) => tEncoder.encode(t)
    }
}