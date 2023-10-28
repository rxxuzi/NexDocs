package global
import nex.Dox

import java.nio.file.{Files, Paths}
import java.nio.file.attribute.BasicFileAttributes
import java.util.Comparator
import java.nio.file.FileVisitResult._
import java.nio.file.FileVisitOption._
import java.nio.file.SimpleFileVisitor

object Operation {
  val OUTPUT_DIR = "./output"
  val host = 4L
  def rmdir(root: String): Unit = {
    val directoryPath = Paths.get(root)
    if (Files.exists(directoryPath) && Files.isDirectory(directoryPath)) {
      Files.newDirectoryStream(directoryPath).forEach { path =>
        if (!Files.isDirectory(path)) {
          Files.deleteIfExists(path)
        }
      }
    }
  }

  def loadFileContent(path: String): String = {
    val source = scala.io.Source.fromFile(path)
    try source.mkString finally source.close()
  }

  def loadFileContent(path: String, encoding: String): String = {
    val source = scala.io.Source.fromFile(path, encoding)
    try source.mkString finally source.close()
  }

  def switchFileType(path: String , content : String): Dox = {
    import nex._
    val file = Paths.get(path)
    val attributes = Files.readAttributes(file, classOf[BasicFileAttributes])
    val fileType = attributes.fileKey()
    fileType match {
      case "md" => NexMarkdown(content)
      case "html" => NexHTML(content)
      case "xhtml" => NexXHTML(content)
      case _ => NexPlain(content)
    }
  }
}

