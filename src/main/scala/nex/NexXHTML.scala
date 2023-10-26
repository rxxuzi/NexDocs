package nex

class NexXHTML(val source: String) extends Dox(source) {
  override val EXT: String = ".xhtml"
}

object NexXHTML {
  def apply(source: String) = new NexXHTML(source)
  def escapeUnescapedAmpersands(inputHtml: String): String = {
    val pattern = "&(?!(?:amp|lt|gt|quot|apos);)"
    inputHtml.replaceAll(pattern, "&amp;")
  }
}