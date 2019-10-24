ThisBuild / name         := "TransactionsExercise"
ThisBuild / organization := "com.colofabrix.scala.designpatterns"
ThisBuild / version      := "1.0.0"
ThisBuild / scalaVersion := "2.13.0"

// Limit how much memory SBT will use
javaOptions in Universal ++= Seq(
  "-J-Xms128m",
  "-J-Xmx1g"
)

libraryDependencies ++= Seq(
  // For reading configuration
  "com.github.pureconfig" %% "pureconfig" % "0.12.1",
  // For application logging
  "org.wvlet.airframe" %% "airframe-log" % "19.10.1",
  // For unit testing
  "org.scalatest" %% "scalatest" % "3.0.8" % "test",
  // Just for fun
  "com.github.lalyos" % "jfiglet" % "0.0.8"
)

/*
 * Linting
 * Be aware that this linting leans towards OOP practices
 */
addCompilerPlugin("org.wartremover" %% "wartremover" % "2.4.3" cross CrossVersion.full)

// Allowing warts that are proper of OOP
wartremoverErrors ++= Warts.allBut(
  Wart.Var,
  Wart.Null,
  Wart.Throw,
  Wart.Equals,
  Wart.Overloading,
  Wart.StringPlusAny
)

// Don't check tests
wartremoverExcluded += baseDirectory.value / "src" / "test" / "scala"
