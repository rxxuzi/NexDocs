package nex

import org.jsoup._
import org.jsoup.nodes.{Element, TextNode}
import com.vladsch.flexmark.html2md.converter.HtmlMarkdownWriter
import java.util.regex.Matcher

class NexHTML(private val source: String) extends Dox(source) {
  override val EXT: String = ".html"
}
