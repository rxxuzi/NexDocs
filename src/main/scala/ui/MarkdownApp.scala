package ui

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.layout.{HBox, Priority, VBox}
import javafx.stage.Stage
import ui.Gui._
import javafx.scene.control.{Button, ToolBar}
import javafx.stage.FileChooser
import nex.{NexHTML, NexMarkdown, NexPdf}

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
    val saveButton = new Button("Save as PDF")
    toolBar.getItems.add(saveButton)

    // ボタンのイベントハンドラの設定
    saveButton.setOnAction(_ => {
      // ファイル名をユーザーに入力させるダイアログの表示
      val fileChooser = new FileChooser()
      fileChooser.getExtensionFilters.add(new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf"))
      val file = fileChooser.showSaveDialog(primaryStage)

      if (file != null) {
        // PDFとHTMLを保存
        val fileName = file.getAbsolutePath
        val content = markdownEditor.textArea.getText
        val html = NexMarkdown(content).toHTML
        // HTML に変換して保存
        html.save("test")
        // pdf に変換して保存
        html.toPdf.saveF(fileName)
      }
    })

    val layout = new HBox(5) // HBoxの子要素間のスペーシングを5に設定
    layout.getChildren.addAll(markdownEditor, markdownViewer)
    HBox.setHgrow(markdownEditor, Priority.ALWAYS)
    HBox.setHgrow(markdownViewer, Priority.ALWAYS)

    // レイアウトにツールバーを追加
    val mainLayout = new VBox()
    mainLayout.getChildren.addAll(toolBar, layout)

    val scene = new Scene(mainLayout, 1200, 800)

    primaryStage.setTitle("NexDocs")
    primaryStage.setScene(scene)
    primaryStage.show()
  }
}