package ui.view

import ui.Gui._
import javafx.scene.image.{Image, ImageView}
import javafx.scene.{Scene, control, layout}
import javafx.scene.web.WebView
import javafx.stage.Stage
import javafx.scene.layout.{HBox, VBox}
import javafx.scene.control.TextField
import nex._

import Console._
import java.nio.file.{Files, Paths}

class HtmlViewer(stage: Stage) {
  private val webView = new WebView()
  private val webEngine = webView.getEngine
  private val backButton = new control.Button("Back")
  private val forwardButton = new control.Button("Forward")
  private val urlField = new TextField()
  private val searchButton = new control.Button("search")

  private val toolbar = new HBox(10, backButton, forwardButton, urlField, searchButton)
  private val layout = new VBox(10, toolbar, webView)

  searchButton.setOnAction(_ => webEngine.load(urlField.getText))



  VBox.setVgrow(webView, javafx.scene.layout.Priority.ALWAYS)

  urlField.setPrefWidth(250.0)

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

  webEngine.locationProperty().addListener((_, _, newValue) => urlField.setText(newValue))

  def loadHtmlFromResource(resourcePath: String): Unit = {
    val resourceUrl = getClass.getResource(resourcePath)
    if (resourceUrl != null) {
      val md =
        if (URL == "") Dox.readExternalFilesAsString("./README.md")
        else Dox.readExternalFilesAsString(URL)
      val content = NexMarkdown(md).toHTML.getSource
      NexHTML(content).save("Used")
      webEngine.loadContent(content)
    } else {
      webEngine.loadContent("<h1>Error: Could not load resource</h1>")
    }
  }

  def show(resourcePath : String = "/Sample.html"): Unit = {
    println(GREEN + "Showing report at "+ resourcePath + RESET)
    loadHtmlFromResource(resourcePath)
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