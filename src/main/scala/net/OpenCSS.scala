package net

import java.net.URL
import scala.io.Source
import scala.util.Try

object OpenCSS {
  def fetchCSSFromURL(cssURL: String): String = {
    Try {
      val connection = new URL(cssURL).openConnection
      connection.setRequestProperty("User-Agent", "Mozilla/5.0")
      Source.fromInputStream(connection.getInputStream).mkString
    }.getOrElse("")
  }
}

