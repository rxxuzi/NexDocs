package nex

class NexXHTML(val source: String) extends Dox(source) {
  override val EXT: String = ".xhtml"
}

object NexXHTML {
  def escapeUnescapedAmpersands(inputHtml: String): String = {
    val pattern = "&(?!(?:amp|lt|gt|quot|apos);)"
    inputHtml.replaceAll(pattern, "&amp;")
  }
}