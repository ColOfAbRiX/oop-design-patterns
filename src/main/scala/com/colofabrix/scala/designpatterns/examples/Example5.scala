package com.colofabrix.scala.designpatterns.examples

import com.colofabrix.scala.designpatterns.service._
import com.colofabrix.scala.designpatterns.filters._
import com.colofabrix.scala.designpatterns.loaders._
import com.colofabrix.scala.designpatterns._

/**
 * Showing how to use a singleton to obtain a configuration and how to use a configuration to
 * allow for dynamic behaviour set by the final user
 */
@SuppressWarnings(Array("org.wartremover.warts.PublicInference"))
object Example5 {
  // Initialize everything automatically
  val loader = TransactionLoader(GlobalConfig().loaderType)
  val filter = TransactionFilter(GlobalConfig().filterType)
  val calc = new TransactionCalculator(loader, filter)

  def run(): Unit = {
    println("EXAMPLE 5")
    for (account <- calc.averageByAccount) {
      println("Account %3s -> %3.2f".format(account._1, account._2))
    }
  }
}
