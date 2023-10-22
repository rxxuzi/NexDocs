package nex

import java.util.stream
import scala.io.Source
import scala.reflect.io.File

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

}