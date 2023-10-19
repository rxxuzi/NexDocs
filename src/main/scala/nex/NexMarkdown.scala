package nex

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.data.MutableDataSet

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
    val html = convertHTMLString(source)
    new NexHTML(html)
  }

}
