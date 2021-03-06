/**
 * For copyright information see the LICENSE document.
 */

import sbt._
import sbt.Keys._


object ProjectBuild extends Build {

    val prjSettings = Project.defaultSettings ++ Seq(
        version := "0.1.0",

        scalaVersion := "2.10.3",

        scalacOptions ++= Seq(
            "-unchecked",
            "-feature",
            "-deprecation",
            "-Xlint",
            "-target:jvm-1.6",
            "-encoding", "UTF-8"
        ),
        
        resolvers ++= Seq(
            "snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
            "releases"  at "http://oss.sonatype.org/content/repositories/releases",
            "Mandubian repository snapshots" at "https://github.com/mandubian/mandubian-mvn/raw/master/snapshots/",
            "Mandubian repository releases" at "https://github.com/mandubian/mandubian-mvn/raw/master/releases/"
        ),
        
        libraryDependencies ++= Seq(
            "org.scala-lang" % "scala-reflect" % "2.10.3",
            "org.slf4j" % "slf4j-simple" % "1.6.4",
            "com.typesafe.akka" %% "akka-actor" % "2.2.0",
            "com.typesafe.akka" %% "akka-testkit" % "2.2.0",
            "org.scalatest" %% "scalatest" % "1.9.1" % "test",
            "com.novus" %% "salat" % "1.9.2",
            "com.chuusai" % "shapeless_2.10.2" % "2.0.0-SNAPSHOT" changing(),
            "org.scala-lang" %% "scala-pickling" % "0.8.0-SNAPSHOT",
            "com.twitter" %% "util-eval" % "6.3.6"
        )
    )

    lazy val seed = Project(
        id = "seed", 
        base = file("seed"),
        settings = prjSettings
    ) dependsOn(root, protocol)
    
    lazy val root = Project(
        id = "root", 
        base = file("."),
        settings = prjSettings ++ Seq(
            name := "Entice Server"
        )
    ) dependsOn(protocol)

    lazy val protocol = RootProject(uri("https://github.com/entice/protocol.git#milestone4"))
}