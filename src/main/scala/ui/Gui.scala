package ui

import javafx.application.Application
import javafx.stage.Stage
import net.OpenCSS

object Gui {
  var ENLARGEABLE = true
  var TITLE = "NexDocs"
  val ICON_PATH = "/icon/NEXDOCS.png"
  var CUSUTOM_HTML = false
  var URL = ""
  def run(): Unit = {
    Application.launch(classOf[HtmlViewerApp])
  }
  def run(url: String): Unit = {
    URL = url
    Application.launch(classOf[HtmlViewerApp])
  }
}

class HtmlViewerApp extends Application {
  override def start(primaryStage: Stage): Unit = {
    val viewer = new HtmlViewer(primaryStage)
    viewer.show()
  }
}
