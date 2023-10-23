package net

import nex.NexHTML

import java.io._
import java.net.{HttpURLConnection, URL}
import scala.io.Source


class OpenHTML(val url: String){
  private val OUTPUT_DIR = "./output/"
  private var HTML : String = ""

  def rawHtml : String = {
    val url = new URL(this.url)
    HTML = Source.fromInputStream(url.openStream()).mkString
    HTML
  }
  def html: NexHTML ={
    new NexHTML(rawHtml)
  }

  def save(filename: String): Unit = {
    try {
      val url = new URL(this.url)
      val openConnection = url.openConnection.asInstanceOf[HttpURLConnection]
      openConnection.setAllowUserInteraction(false)
      openConnection.setInstanceFollowRedirects(true)
      openConnection.setRequestMethod("GET")
      openConnection.connect()
      val httpStatusCode = openConnection.getResponseCode
      if (httpStatusCode != HttpURLConnection.HTTP_OK) throw new Exception("HTTP Status " + httpStatusCode)
      val contentType = openConnection.getContentType
      println("Content-Type: " + contentType)
      // Input Stream
      val dataInStream = new DataInputStream(openConnection.getInputStream)
      // Output Stream
      val dataOutStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(OUTPUT_DIR+filename)))
      // Read Data
      val b = new Array[Byte](4096)
      var readByte = 0
      while (-1 != {
        readByte = dataInStream.read(b)
        readByte
      }) {
        dataOutStream.write(b, 0, readByte)
      }
      // Close Stream
      dataInStream.close()
      dataOutStream.close()
    } catch {
      case e: IOException =>
        e.printStackTrace()
      case e: Exception =>
        println(e.getMessage)
        e.printStackTrace()
    }
  }
}

object OpenHTML{
  def apply(url: String): OpenHTML = new OpenHTML(url)
  def apply(url: String, filename: String): OpenHTML = {
    val html = OpenHTML(url)
    html.save(filename)
    html
  }
}