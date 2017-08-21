name := "scalapfui-javafx"

version := "0.0.1"

scalaVersion := "2.12.3"

resolvers += Resolver.mavenLocal

libraryDependencies += "io.github.bertderbecker" %% "scalapfui-core" % "0.0.1"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "1.0.0-MF"
)

resolvers += Resolver.sonatypeRepo("releases")

addCompilerPlugin("com.github.ghik" %% "silencer-plugin" % "0.5")

libraryDependencies += "com.github.ghik" %% "silencer-lib" % "0.5"

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.4")

// if your project uses multiple Scala versions, use this for cross building
addCompilerPlugin("org.spire-math" % "kind-projector" % "0.9.4" cross CrossVersion.binary)

        