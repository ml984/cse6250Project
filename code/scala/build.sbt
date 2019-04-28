lazy val projectName = "cse6250finalProject"
lazy val projectVersion = "1.1.0"
lazy val projectScalaVersion = "2.11.12"

lazy val sparkVersion = "2.3.0"

lazy val commonSettings = Seq(
  name := projectName,
  version := projectVersion,
  scalaVersion := projectScalaVersion,
)


lazy val sparkDependencies = Seq(
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.spark" %% "spark-streaming" % sparkVersion,
  "org.apache.spark" %% "spark-core" % sparkVersion
)


lazy val dependenciesSettings = Seq(
  resolvers ++= Seq(
    "Atlassian Releases" at "https://maven.atlassian.com/public/",
    "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases",
    Resolver.sonatypeRepo("snapshots"),
    Classpaths.typesafeReleases,
    Classpaths.sbtPluginReleases
  ),
  libraryDependencies ++= Seq(
    "ch.qos.logback" % "logback-classic" % "1.1.2",
    "com.databricks" %% "spark-csv" % "1.5.0",
    "com.github.fommil.netlib" % "all" % "1.1.2",
  ) ++
    sparkDependencies,
  dependencyOverrides ++= Seq(
    "org.scala-lang" % "scala-reflect" % projectScalaVersion,
    "org.scala-lang" % "scala-compiler" % projectScalaVersion,
    "org.scala-lang" % "scala-library" % projectScalaVersion,
    "com.univocity" % "univocity-parsers" % "2.5.9",
    "commons-codec" % "commons-codec" % "1.10"
  ),
  excludeDependencies ++= Seq(
    ExclusionRule(organization = "org.slf4j", name = "slf4j-log4j12")
  )
)


lazy val launchSettings = Seq(
  mainClass in(Compile, run) := Some("main.Main")
)


lazy val root = Project(id = projectName, base = file("."))
  .settings(commonSettings: _*)
  .settings(dependenciesSettings: _*)
  .settings(launchSettings: _*)

fork := true