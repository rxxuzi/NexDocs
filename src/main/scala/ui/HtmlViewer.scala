package ui

import ui.Gui._
import javafx.scene.image.Image
import javafx.scene.{Scene, control, layout}
import javafx.scene.web.WebView
import javafx.stage.Stage
import javafx.scene.layout.{HBox, VBox}
import nex._

import Console._
import java.nio.file.{Files, Paths}

class HtmlViewer(stage: Stage) {
  private val webView = new WebView()
  private val webEngine = webView.getEngine
  private val backButton = new control.Button("Back")
  private val forwardButton = new control.Button("Forward")
  private val toolbar = new HBox(10, backButton, forwardButton)
  private val layout = new VBox(10, toolbar, webView)
  VBox.setVgrow(webView, javafx.scene.layout.Priority.ALWAYS)

  backButton.setOnAction(_ => {
    val history = webEngine.getHistory
    if (history.getCurrentIndex > 0) {
      history.go(-1)
    }
  })

  forwardButton.setOnAction(_ => {
    val history = webEngine.getHistory
    if (history.getCurrentIndex < history.getEntries.size() - 1) {
      history.go(1)
    }
  })

  def loadHtmlFromResource(resourcePath: String): Unit = {
    val resourceUrl = getClass.getResource(resourcePath)
    if (resourceUrl != null) {
//      val content = new String(Files.readAllBytes(Paths.get(resourceUrl.toURI)))
      val md = Dox.readFileAsString("Sample.md")
      val content = NexMarkdown(md).toHTML.getSource
      NexHTML(content).save("Used")
      webEngine.loadContent(content)
    } else {
      webEngine.loadContent("<h1>Error: Could not load resource</h1>")
    }
  }

  def show(): Unit = {
    loadHtmlFromResource("/Sample.html")
    try{
      val icon = new Image(getClass.getResourceAsStream(ICON_PATH))
      stage.getIcons.add(icon)
    }catch {
      case _ : Throwable => println(RED + "Could not load icon")
    }
    stage.setScene(new Scene(layout, 800, 600))
    stage.setTitle(TITLE)
    stage.setResizable(ENLARGEABLE)
    if (!ENLARGEABLE && stage.isMaximized) {
      stage.setMaximized(false)
    }
    stage.show()
  }
}
