package com.github.filosganga.shapelesswb

object ManualEncoders {

  implicit val employeeEncoder: CsvEncoder[Employee] = (e: Employee) =>
    List(e.name, e.number.toString, if (e.manager) "yes" else "no")

  implicit val iceCreamEncoder: CsvEncoder[IceCream] = (i: IceCream) =>
    List(i.name, i.numCherries.toString, if (i.inCone) "yes" else "no")


}
