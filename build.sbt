name := "scalapfui-javafx"

organization := "io.github.bertderbecker"

version := "0.0.7"

scalaVersion := "2.12.3"

isSnapshot := true

resolvers += Resolver.mavenLocal

libraryDependencies += "io.github.bertderbecker" %% "scalapfui-core" % "0.0.14"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "1.0.0-MF"
)

resolvers += Resolver.sonatypeRepo("releases")

addCompilerPlugin("com.github.ghik" %% "silencer-plugin" % "0.5")

libraryDependencies += "com.github.ghik" %% "silencer-lib" % "0.5"

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.4")

// if your project uses multiple Scala versions, use this for cross building
addCompilerPlugin("org.spire-math" % "kind-projector" % "0.9.4" cross CrossVersion.binary)

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")

initialize := {
  val _ = initialize.value
  if (sys.props("java.specification.version") != "1.8")
    sys.error("Java 8 is required for this project.")
}