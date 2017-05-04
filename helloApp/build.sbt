// Enable the Lightbend Telemetry (Cinnamon) sbt plugin
lazy val helloAkka = project in file(".") enablePlugins (Cinnamon)

// Add the Cinnamon Agent for run and test
cinnamon in run := true
cinnamon in test := true

name := "helloApp"
version := "1.0.0-SNAPSHOT"
organization := "none"
scalaVersion := "2.11.8"
lazy val akkaVersion = "2.4.17"

enablePlugins(JavaAppPackaging)

scriptClasspath += "../conf"

libraryDependencies ++= {
  val akkaVersion = "2.4.9"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
    "org.scalatest" %% "scalatest" % "2.2.6" % "test",
    // Use Coda Hale Metrics and Akka instrumentation
    Cinnamon.library.cinnamonCHMetrics,
    Cinnamon.library.cinnamonAkka
  )
}
