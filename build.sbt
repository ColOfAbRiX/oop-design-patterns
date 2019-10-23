ThisBuild / name         := "transactions"
ThisBuild / organization := "com.colofabrix.scala.designpatterns"
ThisBuild / version      := "1.0.0"
ThisBuild / scalaVersion := "2.13.0"

libraryDependencies ++= Seq(
  "com.github.pureconfig"      %% "pureconfig"      % "0.12.1",
  "com.typesafe.scala-logging" %% "scala-logging"   % "3.9.2",
  "ch.qos.logback"             %  "logback-classic" % "1.2.3",
  "org.scalatest"              %% "scalatest"       % "3.0.8" % "test"
)

// Linting
// Be aware that this linting leans towards OOP practices
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
