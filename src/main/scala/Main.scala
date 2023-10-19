import nex._

object Main {
  def main(args: Array[String]): Unit = {
    val htm2 = new NexHTML(Dox.readFileAsString("Sample.html"))

    val md2 = htm2.toMarkdown
    md2.save("sample")
  }
}