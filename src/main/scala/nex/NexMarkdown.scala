package nex

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.data.MutableDataSet
import com.vladsch.flexmark.ext.tables.TablesExtension
import com.vladsch.flexmark.util.misc.Extension
import net.OpenCSS
import com.vladsch.flexmark.ast.FencedCodeBlock
import com.vladsch.flexmark.util.ast.NodeVisitor
import com.vladsch.flexmark.util.ast.VisitHandler
import java.util
import java.util.Collections
import scala.io.Source

final class NexMarkdown (private val source: String) extends Dox(source) {
  override val EXT: String = ".md"
  var usedLanguages: Set[String] = Set()

  /**
   * Converts the given markdown string to HTML.
   *
   * @param markdown The markdown string to convert.
   * @return The converted HTML string.
   */
  def convertHTMLString(markdown: String): String = {
    val options = new MutableDataSet()

    options.set(Parser.EXTENSIONS, Collections.singleton[Extension](TablesExtension.create()))
    val parser = Parser.builder(options).build()
    val renderer = HtmlRenderer.builder(options).build()

    // Parse the markdown
    val document = parser.parse(markdown)

    // Render the parsed markdown to HTML
    renderer.render(document)
  }

  /**
   * @deprecated
   * @param markdown The markdown string to extract languages from.
   * @return
   */
  def extractUsedLanguages(markdown: String): Set[String] = {
    val options = new MutableDataSet()
    options.set(Parser.EXTENSIONS, Collections.singleton[Extension](TablesExtension.create()))
    val parser = Parser.builder(options).build()

    val document = parser.parse(markdown)

    var usedLanguages: Set[String] = Set()
    val visitor = new NodeVisitor(
      new VisitHandler(classOf[FencedCodeBlock], (node: FencedCodeBlock) => {
        val language = node.getInfo.toString.trim
        if (language.nonEmpty) {
          usedLanguages += language
        }
      })
    )
    visitor.visit(document)

    usedLanguages
  }

  def toHTML : NexHTML = {
    val convertedHtmlContent = convertHTMLString(source)
//    val convertedHtmlContent = extractUsedLanguages(source)
    val jsContent = Dox.readFileAsString("javascript/codes.js")
    val scriptTags = NexJS.getScriptTags(usedLanguages)
    val js = Seq(
      "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/highlight.min.js\"></script>",
      "<script>hljs.configure({noHighlightRe: /^$/});</script>",
      "<script type=\"text/javascript\" src=\"http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML\"></script>",
      "<script type=\"text/x-mathjax-config\">\nMathJax.Hub.Config({ tex2jax: {inlineMath: [['$', '$']]}, messageStyle: \"none\" });\n</script>"
    )

    var fullHtml =
      s"""
         |<html>
         |<head>
         |    <meta charset="utf-8" />
         |    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
         |    <meta name="viewport" content="width=device-width, initial-scale=1" />
         |    <link rel="stylesheet" href="${OpenCSS.DRACULA}">
         |    ${js.mkString("\n    ")}
         |    $scriptTags
         |
         |</head>
         |<body>
         |$convertedHtmlContent
         |<script>
         |$jsContent
         |</script>
         |</body>
         |</html>
        """.stripMargin

    fullHtml = addStyles(fullHtml, css)
    fullHtml = addStyles(fullHtml, "stylesheet/code.css")
    new NexHTML(fullHtml)
  }

  // CSSをHTMLに適用するメソッド
  def addStyles(html: String , css : String): String = {
    val cssContent = Source.fromResource(css).getLines().mkString
    val styledHtml = html.replace("</head>", s"<style>\n$cssContent\n</style>\n</head>")
    styledHtml
  }
}

object NexMarkdown {
  def apply(source: String) = new NexMarkdown(source)
}
