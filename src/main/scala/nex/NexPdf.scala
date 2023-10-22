package nex

import org.xhtmlrenderer.pdf.ITextRenderer
import global.LogConfig
import java.io.{FileOutputStream, OutputStream}

class NexPdf(private val source: String) extends Dox(source) {
  override val EXT: String = ".pdf"

  override def save(fileName: String): Unit = {
    val outputStream = new FileOutputStream(PATH+fileName+EXT)
    LogConfig.suppressFlyingSaucerLogs()
    convert(source, outputStream)
  }

  def convert(html: String, outputStream: OutputStream): Unit = {
    val renderer = new ITextRenderer()
    renderer.setDocumentFromString(html)
    renderer.layout()
    renderer.createPDF(outputStream)
    outputStream.close()
  }
}