addSbtPlugin("ch.epfl.scala" % "sbt-scalafix" % "0.5.10")
addSbtPlugin("io.get-coursier" % "sbt-coursier" % "1.0.2")
addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.8.0")

logLevel := Level.Warn

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.9.0")
addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.3.3") // Run with `sbt dependencyUpdates`
addSbtPlugin("org.wartremover" % "sbt-wartremover" % "2.2.1") // https://github.com/puffnfresh/wartremover/issues/294
addSbtPlugin("org.wartremover" % "sbt-wartremover-contrib" % "1.1.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-git" % "0.9.3")
addSbtPlugin("net.vonbuchholtz" % "sbt-dependency-check" % "0.2.0")
