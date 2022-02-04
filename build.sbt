ThisBuild / resolvers ++= Seq(
    "Apache Development Snapshot Repository" at "https://repository.apache.org/content/repositories/snapshots/",
    Resolver.mavenLocal
)

name := "hw_flink-1"

version := "1"

organization := "org.example"

ThisBuild / scalaVersion := "2.12.8"

val flinkVersion = "1.14.3"

val flinkDependencies = Seq(
  "org.apache.flink" %% "flink-clients" % flinkVersion % "provided",
  "org.apache.flink" %% "flink-scala" % flinkVersion % "provided",
  "org.apache.flink" %% "flink-streaming-scala" % flinkVersion % "provided")

lazy val root = (project in file(".")).
  settings(
    libraryDependencies ++= flinkDependencies
  )

assembly / mainClass := Some("org.example.Job")

Compile / run  := Defaults.runTask(Compile / fullClasspath,
                                   Compile / run / mainClass,
                                   Compile / run / runner
                                  ).evaluated

Compile / run / fork := true
Global / cancelable := true
assembly / assemblyOption  := (assembly / assemblyOption).value.copy(includeScala = false)
