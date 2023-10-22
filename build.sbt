ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "NexDocs"
  )

libraryDependencies += "com.vladsch.flexmark" % "flexmark-all" % "0.64.6"
libraryDependencies += "org.scalafx" %% "scalafx" % "16.0.0-R25"
libraryDependencies += "org.jsoup" % "jsoup" % "1.15.4"

libraryDependencies += "org.xhtmlrenderer" % "flying-saucer-core" % "9.1.22"
libraryDependencies += "org.xhtmlrenderer" % "flying-saucer-pdf" % "9.1.22"
libraryDependencies += "com.itextpdf" % "itextpdf" % "5.5.13"

