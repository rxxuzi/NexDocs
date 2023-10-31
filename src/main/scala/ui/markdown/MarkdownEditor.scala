package ui.markdown

import javafx.scene.control.TextArea
import javafx.scene.layout.{Priority, VBox}

class MarkdownEditor extends VBox {
  val textArea = new TextArea()
  textArea.setPrefHeight(600) // テキストエリアの初期高さを設定
  textArea.setStyle("-fx-control-inner-background: black; -fx-text-fill: white;")
  getChildren.add(textArea)
  VBox.setVgrow(textArea, Priority.ALWAYS) // テキストエリアがVBoxの高さに合わせて伸縮するように設定
}
