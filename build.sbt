name := "shapeless-wb"
organization := "com.github.filosganga"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
  "com.chuusai" %% "shapeless" % "2.3.2",
  "org.scalatest" %% "scalatest" % "3.0.1" % Test,
  "org.scalacheck" %% "scalacheck" % "1.13.4" % Test

)

initialCommands in console := """
                                |import com.github.filosganga.shapelesswb._;
                                |""".stripMargin