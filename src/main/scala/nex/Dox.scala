package nex

import org.jsoup.Jsoup

import java.util.stream
import scala.io.Source
import scala.reflect.io.File
import scala.util.Try

abstract class Dox(private val source: String) {
  val PATH = "./output/"
  val EXT = ".dox"
  val css : String = "stylesheet/nex.css"
  def getSource: String = source
  def getLines: stream.Stream[String] = source.lines
  def getLinesIterator: Iterator[String] = source.linesIterator

  def save(fileName: String): Unit = {
    val file: File = File(PATH + fileName + EXT)
    if (!file.exists) {
      file.createFile()
    }
    file.writeAll(source)
  }

  override def toString: String = source + "\n"

}

object Dox{
  /**
   * Reads the content of a file as a string.
   *
   * @param path The path to the file.
   * @return The content of the file as a string.
   */
  def readFileAsString(path: String): String = {
    val source = Source.fromResource(path) // Use fromResource for resources inside the classpath
    try source.mkString finally source.close()
  }

  def readExternalFilesAsString(path: String): String = {
    val file = new java.io.File(path)
    val source = new StringBuilder()

    if (file.exists) {
      val reader = new java.io.BufferedReader(new java.io.FileReader(file))
      var line = reader.readLine()
      while (line != null) {
        source.append(line)
        source.append("\n") // Add newline for each line
        line = reader.readLine()
      }
      reader.close()
    }

    source.toString()
  }

  implicit class RichString(val s: String) extends AnyVal {
    def getHtmlTitle: String = {
      Try {
        val doc = Jsoup.parse(s)
        doc.title()
      }.get
    }

    def toHTML: NexHTML = new NexHTML(s)
    def toMarkdown: NexMarkdown = new NexMarkdown(s)
  }
}

