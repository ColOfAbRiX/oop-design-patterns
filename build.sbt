/*
 * Main project settings
 */

lazy val root = (project in file("."))
  .enablePlugins(BuildInfoPlugin)
  .settings(
    name             := "Transactions",
    organization     := "com.colofabrix.scala.designpatterns",
    version          := "1.0.0",
    scalaVersion     := "2.13.0",
    buildInfoKeys    := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
    buildInfoPackage := organization.value
  )

// Limit how much memory SBT will use
javaOptions in Universal ++= Seq(
  "-J-Xms128m",
  "-J-Xmx1g"
)


/*
 * Dependencies
 */
libraryDependencies ++= Seq(
  "com.github.pureconfig" %% "pureconfig" % "0.12.1",
  "org.wvlet.airframe" %% "airframe-log" % "19.10.1",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test",
  "org.scalacheck" %% "scalacheck" % "1.14.1" % "test",
  "com.github.lalyos" % "jfiglet" % "0.0.8"
)


/*
 * Linting. Be aware that this linting leans towards OOP practices
 */

addCompilerPlugin("org.wartremover" %% "wartremover" % "2.4.3" cross CrossVersion.full)

// Allowing warts that are proper of OOP
wartremoverErrors ++= Warts.allBut(
  Wart.Var,
  Wart.Null,
  Wart.Throw,
  Wart.While,
  Wart.Equals,
  Wart.TryPartial,
  Wart.Overloading,
  Wart.StringPlusAny,
  Wart.NonUnitStatements
)

// Don't check tests
wartremoverExcluded += baseDirectory.value / "src" / "test" / "scala"
