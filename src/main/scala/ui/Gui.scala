package ui

import global.Operation
import javafx.application.Application
import javafx.stage.Stage
import net.OpenCSS

object Gui {
  var ENLARGEABLE = true
  var TITLE = "NexDocs"
  val ICON_PATH = "/icon/NEXDOCS.png"
  var CUSUTOM_HTML = false
  var URL = ""

  val md: Class[MarkdownApp] = classOf[MarkdownApp]

  def run(): Unit = {
    Operation.rmdir(Operation.OUTPUT_DIR)
    Application.launch(md)
  }
  def run(url: String): Unit = {
    URL = url
    Application.launch(md)
  }
}
