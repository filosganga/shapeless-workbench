package com.github.filosganga.shapelesswb

sealed trait Shape
final case class Rectangle(width: Double, height: Double) extends Shape
final case class Circle(radius: Double) extends Shape

object Shape {

  def area(shape: Shape): Double =
    shape match {
      case Rectangle(w, h) => w * h
      case Circle(r)       => math.Pi * r * r
    }
}