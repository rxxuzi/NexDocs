package global
import java.util.logging.{Level, Logger}

object LogConfig {
  def suppressFlyingSaucerLogs(): Unit = {
    val loggerNames = List(
      "org.xhtmlrenderer",
      "org.xhtmlrenderer.load",
      "org.xhtmlrenderer.css-parse",
      "org.xhtmlrenderer.match"
    )

    loggerNames.foreach { loggerName =>
      val logger = Logger.getLogger(loggerName)
      logger.setLevel(Level.SEVERE)
    }
  }
}
