package ui.markdown

import javafx.scene.layout.{Priority, VBox}
import javafx.scene.web.WebView
import nex.NexMarkdown

class MarkdownViewer extends VBox {
  val webView = new WebView()
  getChildren.add(webView)
  VBox.setVgrow(webView, Priority.ALWAYS) // WebViewがVBoxの高さに合わせて伸縮するように設定

  def updateContent(markdown: String): Unit = {
    val htmlContent = NexMarkdown(markdown).toHTML.toString
    webView.getEngine.loadContent(htmlContent)
  }
}
