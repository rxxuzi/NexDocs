package ui.markdown

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.{Button, ToolBar}
import javafx.scene.image.Image
import javafx.scene.layout.{HBox, Priority, VBox}
import javafx.stage.{FileChooser, Stage}
import nex.NexMarkdown
import ui.Gui._

import scala.Console.RED


class MarkdownApp extends Application {
  override def start(primaryStage: Stage): Unit = {
    val markdownEditor = new MarkdownEditor()
    val markdownViewer = new MarkdownViewer()

    try {
      val icon = new Image(getClass.getResourceAsStream(ICON_PATH))
      primaryStage.getIcons.add(icon)
    } catch {
      case _: Throwable => println(RED + "Could not load icon")
    }

    // テキストエリアの内容が変更されたときに、ビューアを更新
    markdownEditor.textArea.textProperty.addListener((_, _, newValue) => {
      markdownViewer.updateContent(newValue)
    })

    // ツールバーの作成
    val toolBar = new ToolBar()

    // 「save as pdf」ボタンの作成
    val saveAsPdf = new Button("Save as PDF")
    toolBar.getItems.add(saveAsPdf)

    // ボタンのイベントハンドラの設定
    saveAsPdf.setOnAction(_ => {
      // ファイル名をユーザーに入力させるダイアログの表示
      val fileChooser = new FileChooser()
      fileChooser.getExtensionFilters.add(new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf"))
      val file = fileChooser.showSaveDialog(primaryStage)

      if (file != null) {
        // PDFとHTMLを保存
        val fileName = file.getAbsolutePath
        val content = markdownEditor.textArea.getText
        val html = NexMarkdown(content).toHTML
        // pdf に変換して保存
        html.toPdf.saveF(fileName)
      }
    })

    val saveAsHtml = new Button("Save as HTML")
    toolBar.getItems.add(saveAsHtml)
    saveAsHtml.setOnAction(_ => {
      val fileChooser = new FileChooser()
      fileChooser.getExtensionFilters.add(new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html"))
      val file = fileChooser.showSaveDialog(primaryStage)
      if (file != null) {
        val fileName = file.getAbsolutePath
        val content = markdownEditor.textArea.getText
        val html = NexMarkdown(content).toHTML
        html.saveF(fileName)
        println(fileName)
      }
    })

    val layout = new HBox(5) // HBoxの子要素間のスペーシングを5に設定
    layout.getChildren.addAll(markdownEditor, markdownViewer)
    HBox.setHgrow(markdownEditor, Priority.ALWAYS)
    HBox.setHgrow(markdownViewer, Priority.ALWAYS)

    // レイアウトにツールバーを追加
    val mainLayout = new VBox()
    mainLayout.getChildren.addAll(toolBar, layout)
    VBox.setVgrow(layout, Priority.ALWAYS)


    val scene = new Scene(mainLayout, 1200, 800)

    primaryStage.setTitle("NexDocs")
    primaryStage.setScene(scene)
    primaryStage.show()
  }
}