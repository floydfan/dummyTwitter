import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "myApp"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    "postgresql" % "postgresql" % "8.4-702.jdbc4",
    "org.twitter4j" % "twitter4j-core" % "3.0.3",
    "mysql" % "mysql-connector-java" % "5.1.18"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    resolvers += "JBoss repository" at "https://repository.jboss.org/nexus/content/repositories/",
    resolvers += "Scala-Tools Maven2 Snapshots Repository" at
      "http://scala-tools.org/repo-snapshots",
    resolvers += "twitter4j-repo" at "http://twitter4j.org/maven2"
  )

}
