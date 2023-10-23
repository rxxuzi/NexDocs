package nex

import net.OpenCSS
import nex.NexXHTML.escapeUnescapedAmpersands
import org.jsoup.Jsoup
import org.jsoup.nodes.Document.OutputSettings
import org.jsoup.nodes.{Element, Node, TextNode}

import scala.io.Source
import scala.jdk.CollectionConverters.CollectionHasAsScala


class NexHTML(private var source: String) extends Dox(source) {
  override val EXT: String = ".html"

  def toMarkdown : NexMarkdown = {
    val document = Jsoup.parse(source)
    document.select("style, script").remove()
    val md = NexHTML.convertNode(document.body())
    new NexMarkdown(md)
  }

  def toPdf : NexPdf = {
    val xhtml = toXHTML
    new NexPdf(xhtml.getSource)
  }

  def toXHTML: NexXHTML = {
    // Embed the external CSS
    var processedHtml = embedExternalCSS()
    processedHtml = escapeUnescapedAmpersands(processedHtml)
    processedHtml = removeDuplicateRoleAttributes(processedHtml)

    val doc = Jsoup.parse(processedHtml)
    doc.outputSettings().syntax(OutputSettings.Syntax.xml)
    new NexXHTML(doc.html())
  }

  def toPlainText : NexPlain = {
    val plainText = getRawDocument
    new NexPlain(plainText)
  }

  // CSSをHTMLに適用するメソッド
  def applyStyles(): Unit = {
    val cssContent = Source.fromResource(css).getLines()
    val styledHtml = source.replace("</head>", s"<style>\n$cssContent\n</style>\n</head>")
    source = styledHtml
  }

  def getRawDocument : String = {
    val document = Jsoup.parse(source)
    document.select("style, script").remove()
    NexHTML.convertNode(document.body())
  }

  def removeJavaScript(): NexHTML = {
    val document = Jsoup.parse(source)
    document.select("script").remove()
    source = document.html()
    new NexHTML(source)
  }

  def removeAtag(inputHtml : String): String = {
    val document = Jsoup.parse(inputHtml)
    document.select("a").remove()
    document.html()
  }

  def removeDuplicateRoleAttributes(inputHtml: String): String = {
    val pattern = "(<form[^>]*?role=[^>]*?)role=".r
    pattern.replaceAllIn(inputHtml, m => m.group(1))
  }


  def embedExternalCSS(): String = {
    val doc = Jsoup.parse(source)
    val linkElements = doc.select("link[rel=stylesheet][href]")

    linkElements.forEach { linkElement: Element =>
      val cssURL = linkElement.attr("abs:href") // Convert relative URL to absolute URL
      val cssContent = OpenCSS.fetchCSSFromURL(cssURL)

      if (cssContent.nonEmpty) {
        // Create a new <style> element and set its content to the fetched CSS only if it's non-empty
        val styleElement = doc.createElement("style")
        styleElement.appendText(cssContent)

        // Add the new <style> element to the <head>
        doc.head().appendChild(styleElement)
      }

      // Remove the original <link> element
      linkElement.remove()
    }

    doc.outerHtml()
  }

  def getTitle: String = {
    val doc = Jsoup.parse(source)
    doc.title()
  }

}

object NexHTML {
  def convertNode(node: Node): String = node match {
    case textNode: TextNode => textNode.text()
    case e: Element => e.tagName() match {
      case "b" | "strong" => s"**${e.text()}**"
      case "i" |  "em"    => s"*${e.text()}*"
      case "u" |  "ins"   => s"_${e.text()}_"
      case "s" |  "del"   => s"~~${e.text()}~~"
      case "a" => s"[${e.text()}](${e.attr("href")})"

      case "br" => s"\n"
      case "h1" => s"# ${e.text()}\n\n"
      case "h2" => s"## ${e.text()}\n\n"
      case "h3" => s"### ${e.text()}\n\n"
      case "h4" => s"#### ${e.text()}\n\n"
      case "h5" => s"##### ${e.text()}\n\n"
      case "h6" => s"###### ${e.text()}\n\n"

      case "blockquote" => s"> ${e.html()}\n\n"
      case "hr" => s"\n---\n\n"

      case "pre" =>
        val code = e.selectFirst("code")
        if (code != null) {
          val lines = code.wholeText.split("\n")
          val minIndentLength = lines.filter(_.trim.nonEmpty).map(line => line.takeWhile(_.isWhitespace).length).minOption.getOrElse(0)
          val adjustedCode = lines.map(line => {
            if (line.length > minIndentLength) line.drop(minIndentLength) else line
          }).mkString("\n").trim
          s"~~~\n$adjustedCode\n~~~\n"
        } else {
          s"```\n${e.text()}\n```\n"
        }

      case "li" => e.parent().tagName() match {
        case "ol" => s"1. ${e.html()}\n"
        case "ul" => s"* ${e.html()}\n"
        case _ => s"* ${e.html()}\n"
      }

      case "p" => s"${e.html()}\n\n"

      case "table" =>
        val headers = e.selectFirst("thead").select("tr").select("td, th").toArray.map {
          case cell: Element => cell.html()
        }.mkString("|", "|", "|")

        val separator = e.selectFirst("thead").select("tr").select("td, th").toArray.map(_ => ":---:").mkString("|", "|", "|")

        val rows = e.selectFirst("tbody").select("tr").toArray.map {
          case row: Element => row.select("td, th").toArray.map {
            case cell: Element => cell.html()
          }.mkString("|", "|", "|")
        }.mkString("\n")

        s"\n$headers\n$separator\n$rows\n"

      case _ => e.children().asScala.map(convertNode).mkString
    }
  }
}