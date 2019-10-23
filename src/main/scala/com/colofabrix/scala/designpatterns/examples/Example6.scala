package com.colofabrix.scala.designpatterns.examples

import com.colofabrix.scala.designpatterns.service._
import com.colofabrix.scala.designpatterns.filters._
import com.colofabrix.scala.designpatterns.loaders._
import wvlet.log.LogSupport
import com.colofabrix.scala.designpatterns._

/**
 * Showing how to use a logging library
 */
@SuppressWarnings(Array("org.wartremover.warts.PublicInference"))
object Example6 extends LogSupport {
  info("Starting EXAMPLE 6")

  val loader = TransactionLoader(GlobalConfig().loaderType)
  debug(s"Created new TransactionLoader: ${GlobalConfig().loaderType}")

  val filter = TransactionFilter(GlobalConfig().filterType)
  debug(s"Created new TransactionFilter: ${GlobalConfig().filterType}")

  val calc = new TransactionCalculator(loader, filter)
  info(s"Created new TransactionCalculator")

  def run(): Unit = {
    for (account <- calc.averageByAccount) {
      info("Account %3s -> %3.2f".format(account._1, account._2))
    }

    try {
      throw new RuntimeException("An exception just to display an error in the logs")
    }
    catch {
      case e: Exception =>
        error(s"Error while processing: ${e.getMessage}")
    }
  }
}
