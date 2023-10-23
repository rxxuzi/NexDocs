package global
import java.nio.file.{Files, Paths}
import java.nio.file.attribute.BasicFileAttributes
import java.util.Comparator
import java.nio.file.FileVisitResult._
import java.nio.file.FileVisitOption._
import java.nio.file.SimpleFileVisitor

case object Operation {
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
}

