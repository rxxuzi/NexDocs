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

  def cssStyle(cssType: String): String = {
    s"<link rel=\"stylesheet\" href=\"$cssType\">"
  }

  def codeBlockStyle : String = {
    Source.fromResource("stylesheets/code.css").mkString
  }

  //  CSS Links
  val DRACULA = "https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/styles/atom-one-dark.min.css"
  val ATOM_ONE_DARK = "https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/styles/atom-one-dark.min.css"
  val MONOKAI = "https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/styles/monokai.min.css"
  val SOLARIZED_LIGHT = "https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/styles/solarized-light.min.css"
  val SOLARIZED_DARK = "https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/styles/solarized-dark.min.css"
  val TOMORROW_NIGHT_BLUE = "https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/styles/tomorrow-night-blue.min.css"
  val TOMORROW_NIGHT_BRIGHT = "https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/styles/tomorrow-night-bright.min.css"
  val TOMORROW_NIGHT_EIGHTIES = "https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/styles/tomorrow-night-eighties.min.css"
  val TOMORROW_NIGHT = "https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/styles/tomorrow-night.min.css"
  val TOMORROW_NIGHT_GRAY = "https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/styles/tomorrow-night-gray.min.css"
  val TOMORROW_NIGHT_LIGHT = "https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/styles/tomorrow-night-light.min.css"
  val TOMORROW_NIGHT_LIGHTER = "https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/styles/tomorrow-night-lighter.min.css"
  val TOMORROW_NIGHT_MAGENTA = "https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/styles/tomorrow-night-magenta.min.css"
  val TOMORROW_NIGHT_RED = "https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/styles/tomorrow-night-red.min.css"
}

