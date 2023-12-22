ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.1"

libraryDependencies += "net.dv8tion" % "JDA" % "5.0.0-beta.18"

lazy val root = (project in file("."))
  .settings(
    name := "dc-weatherbot"
  )
