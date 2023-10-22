package nex

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.data.MutableDataSet

import scala.io.Source

class NexMarkdown (private val source: String) extends Dox(source) {
  override val EXT: String = ".md"

  /**
   * Converts the given markdown string to HTML.
   *
   * @param markdown The markdown string to convert.
   * @return The converted HTML string.
   */
  def convertHTMLString(markdown: String): String = {
    val options = new MutableDataSet()
    val parser = Parser.builder(options).build()
    val renderer = HtmlRenderer.builder(options).build()

    // Parse the markdown
    val document = parser.parse(markdown)

    // Render the parsed markdown to HTML
    renderer.render(document)
  }

  def toHTML : NexHTML = {
    val convertedHtmlContent = convertHTMLString(source)

    val fullHtml =
      s"""
         |<html>
         |<head>
         |    <meta charset="utf-8" />
         |    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
         |    <meta name="viewport" content="width=device-width, initial-scale=1" />
         |</head>
         |<body>
         |$convertedHtmlContent
         |</body>
         |</html>
        """.stripMargin

    new NexHTML(addStyles(fullHtml))
  }

  // CSSをHTMLに適用するメソッド
  def addStyles(html: String): String = {
    val cssContent = Source.fromResource(css).getLines().mkString
    val styledHtml = html.replace("</head>", s"<style>\n$cssContent\n</style>\n</head>")
    styledHtml
  }

}
