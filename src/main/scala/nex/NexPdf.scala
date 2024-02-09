package nex

import com.lowagie.text.pdf.BaseFont
import nex.Dox._
import org.xhtmlrenderer.pdf.ITextRenderer

import java.io.FileOutputStream

final class NexPdf(private val source: String) extends Dox(source) {
  override val EXT: String = ".pdf"

  override def save(fileName: String): Unit = {
      val renderer = convert(source.toHTML)
      val os = new FileOutputStream(PATH + fileName + EXT)
      renderer.createPDF(os)
      os.close()
  }

  override def saveF(path: String): Unit = {
      val renderer = convert(source.toHTML)
      val os = new FileOutputStream(path)
      renderer.createPDF(os)
      os.close()
  }

  private def convert(html: NexHTML): ITextRenderer = {
    val renderer = new ITextRenderer()

    // NotoフォントをITextRendererに登録
    val fontPath = "font/NotoSansJP-VariableFont_wght.ttf" // リソース内のフォントへのパス
    renderer.getFontResolver.addFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED)

    val xhtml = html.toXHTML.toString
    renderer.setDocumentFromString(xhtml)
    renderer.layout()
    renderer
  }
}

object NexPdf {
  def apply(source: String) = new NexPdf(source)
}

