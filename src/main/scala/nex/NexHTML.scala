package nex

import org.jsoup.Jsoup
import org.jsoup.nodes.{Element, Node, TextNode}

import scala.jdk.CollectionConverters.CollectionHasAsScala


class NexHTML(private val source: String) extends Dox(source) {
  override val EXT: String = ".html"

  def toMarkdown : NexMarkdown = {
    val document = Jsoup.parse(source)
    document.select("style, script").remove()
    val md = NexHTML.convertNode(document.body())
    new NexMarkdown(md)
  }

  def getBody : String = {
    val document = Jsoup.parse(source)
    document.select("style, script").remove()
    NexHTML.convertNode(document.body())
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

      case "pre" => {
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
      }

      case "li" => e.parent().tagName() match {
        case "ol" => s"1. ${e.html()}\n"
        case "ul" => s"* ${e.html()}\n"
        case _ => s"* ${e.html()}\n"
      }

      case "p" => s"${e.html()}\n\n"

      case "table" => {
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
      }

      case _ => e.children().asScala.map(convertNode).mkString
    }
  }
}