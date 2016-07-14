import play.PlayJava

name := "playframework-tests-example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

javaOptions in Test ++= Seq("-Dconfig.file=test/conf/test.conf")

libraryDependencies ++= Seq(
  javaJdbc,
  "org.projectlombok" % "lombok" % "1.16.8"
)