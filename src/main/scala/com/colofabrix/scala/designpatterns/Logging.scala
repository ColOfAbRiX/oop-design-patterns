package com.colofabrix.scala.designpatterns

import wvlet.log._
import com.github.lalyos.jfiglet._

/**
 * Initializes and configures airframe-log for logging
 */
object Logging extends LogSupport {
  def init(): Unit = {
    Logger.scheduleLogLevelScan
    // Default formatter for screen
    Logger.setDefaultFormatter(LogFormatter.SourceCodeLogFormatter)
    // Adding a new global handler to write output to a file
    Logger.rootLogger.addHandler(new FileHandler(
      fileName = "application.log",
      formatter = LogFormatter.PlainSourceCodeLogFormatter
    ))

    // Write pretty introductory message
    val appName = getClass.getPackage.getImplementationTitle
    info("Welcome to")
    info(FigletFont.convertOneLine("classpath:/larry3d.flf", appName))
  }
}
