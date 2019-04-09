
organization := "devchallenge.other"
name := "tester"
version := "0.1-SNAPSHOT"

scalaVersion := "2.12.8"
libraryDependencies ++= Seq(
  "com.softwaremill.sttp" %% "core" % "1.5.11",
  "com.softwaremill.sttp" %% "akka-http-backend" % "1.5.11",
  "com.typesafe.akka" %% "akka-http" % "10.1.7",
  "com.typesafe.akka" %% "akka-stream" % "2.5.22",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)
