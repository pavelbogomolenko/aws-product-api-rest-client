name := "aws-product-api-rest-client"

version := "1.0"

scalaVersion := "2.10.3"

resolvers ++= Seq()

libraryDependencies ++= Seq(
  "net.databinder.dispatch" % "dispatch-core_2.10" % "0.11.0",
  "joda-time" % "joda-time" % "2.3",
  "org.joda" % "joda-convert" % "1.5",
  "commons-codec" % "commons-codec" % "1.6",
  "org.apache.httpcomponents" % "httpcore" % "4.3",
  "log4j" % "log4j" % "1.2.16" % "test->default"
)

/** Compilation */
javacOptions ++= Seq()

javaOptions += "-Xmx2G"

scalacOptions ++= Seq("-deprecation", "-unchecked")
