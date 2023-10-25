package ui

import javafx.application.Application
import javafx.stage.Stage
import net.OpenCSS

object Gui {
  var ENLARGEABLE = true
  var TITLE = "NexDocs"
  val ICON_PATH = "/icon/NEXDOCS.png"
  def main(args: Array[String]): Unit = {
    Application.launch(classOf[HtmlViewerApp], args: _*)
  }
}

class HtmlViewerApp extends Application {
  override def start(primaryStage: Stage): Unit = {
    val viewer = new HtmlViewer(primaryStage)
    viewer.show()
  }
}
