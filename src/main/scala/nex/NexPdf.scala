package nex


class NexPdf(private val source: String) extends Dox(source) {
  override val EXT: String = ".pdf"

}