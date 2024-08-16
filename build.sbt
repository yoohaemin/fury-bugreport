lazy val root = project
  .in(file("."))
  .settings(
    name := "Fury bugreport",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := "3.3.3",
    crossScalaVersions := Seq("2.12.19", "2.13.14", "3.3.3", "3.5.0"),
    libraryDependencies += "org.apache.fury" % "fury-core" % "0.7.0"
  )
