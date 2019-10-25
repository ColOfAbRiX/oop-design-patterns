package com.colofabrix.scala.designpatterns

import wvlet.log._
import com.github.lalyos.jfiglet._

/**
 * Initializes and configures airframe-log for logging
 */
object Logging extends LogSupport {
  def init(): Unit = {
    Logger.scheduleLogLevelScan
    // Adding a new global handler to write output to a file
    Logger.setDefaultHandler(new FileHandler(
      fileName = "application.log",
      formatter = LogFormatter.PlainSourceCodeLogFormatter,
    ))

    // Write pretty introductory message
    info("Welcome to")
    info(FigletFont.convertOneLine("classpath:/larry3d.flf", BuildInfo.name))
  }
}
