name := "helloApp"
version := "1.0.0-SNAPSHOT"
organization := "none"
scalaVersion := "2.11.8"

enablePlugins(JavaAppPackaging)

scriptClasspath += "../conf"

libraryDependencies ++= {
  val akkaVersion = "2.4.9"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
    "org.scalatest" %% "scalatest" % "2.2.6" % "test"
  )
}