lazy val V = _root_.scalafix.Versions
// Use a scala version supported by scalafix.
scalaVersion in ThisBuild := V.scala212

enablePlugins(GitVersioning)
git.useGitDescribe := true

lazy val extraScalacOptions = Seq(
  "-feature", "-deprecation", "-unchecked", "-explaintypes",
  "-encoding", "UTF-8", // yes, this is 2 args
  "-language:reflectiveCalls", "-language:implicitConversions", "-language:postfixOps", "-language:existentials",
  "-language:higherKinds",
  // http://blog.threatstack.com/useful-scala-compiler-options-part-3-linting
  "-Xcheckinit", "-Xexperimental", "-Xfatal-warnings", /*"-Xlog-implicits", */ "-Xfuture", "-Xlint",
  "-Ywarn-dead-code", "-Ywarn-inaccessible", "-Ywarn-numeric-widen", "-Yno-adapted-args", "-Ywarn-unused-import",
  "-Ywarn-unused", "-Ypartial-unification"
)

lazy val defaultSettings = Seq(
  wartremoverErrors ++= Seq(
    Wart.StringPlusAny, Wart.FinalCaseClass, Wart.JavaConversions, Wart.Null, Wart.Product, Wart.Serializable,
    Wart.LeakingSealed, Wart.While, Wart.Return, Wart.ExplicitImplicitTypes, Wart.Enumeration, Wart.FinalVal,
    Wart.TryPartial, Wart.TraversableOps, Wart.OptionPartial, ContribWart.SomeApply
  ),

  wartremoverWarnings ++= wartremover.Warts.allBut(
    Wart.Nothing, Wart.DefaultArguments, Wart.Throw, Wart.MutableDataStructures, Wart.NonUnitStatements,
    Wart.Overloading, Wart.Option2Iterable, Wart.ImplicitConversion, Wart.ImplicitParameter, Wart.Recursion
  )
)

lazy val rules = project
  .settings(defaultSettings)
  .settings(
    scalacOptions ++= extraScalacOptions,
    libraryDependencies ++= Seq(
      "ch.epfl.scala" %% "scalafix-core" % V.version
    )
  )

lazy val input = project.settings(
  scalafixSourceroot := sourceDirectory.in(Compile).value
)

lazy val output = project

lazy val tests = project
  .settings(defaultSettings)
  .settings(
    scalacOptions ++= extraScalacOptions,
    libraryDependencies += "ch.epfl.scala" % "scalafix-testkit" % V.version % Test cross CrossVersion.full,
    buildInfoPackage := "fix",
    buildInfoKeys := Seq[BuildInfoKey](
      "inputSourceroot" ->
        sourceDirectory.in(input, Compile).value,
      "outputSourceroot" ->
        sourceDirectory.in(output, Compile).value,
      "inputClassdirectory" ->
        classDirectory.in(input, Compile).value
    )
  )
  .dependsOn(input, rules)
  .enablePlugins(BuildInfoPlugin)
