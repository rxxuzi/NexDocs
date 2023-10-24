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

libraryDependencies ++= Seq(
  "org.openjfx" % "javafx-base" % "17.0.1",
  "org.openjfx" % "javafx-controls" % "17.0.1",
  "org.openjfx" % "javafx-web" % "17.0.1",
  "org.openjfx" % "javafx-fxml" % "17.0.1"
)

fork := true

javaOptions ++= Seq(
  "--module-path", "/path/to/your/javafx/sdk/lib",
  "--add-modules", "javafx.controls,javafx.web"
)


