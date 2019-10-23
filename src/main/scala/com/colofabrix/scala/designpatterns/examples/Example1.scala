package com.colofabrix.scala.designpatterns.examples

import com.colofabrix.scala.designpatterns.service._
import com.colofabrix.scala.designpatterns.filters._
import com.colofabrix.scala.designpatterns.loaders._
import com.colofabrix.scala.designpatterns._

/**
 * Showing how to use the strategy pattern to choose how to do things
 */
@SuppressWarnings(Array("org.wartremover.warts.PublicInference"))
object Example1 {
  // Choose the loader
  val loader = new FileTransactionsLoader("transactions.txt")

  // Choose the filter
  val filter = new AmountRangeTransactionFilter(250, 750)

  // Create the calculator
  val calc = new TransactionCalculator(loader, filter)

  def run(): Unit = {
    println("EXAMPLE 1")
    for (account <- calc.averageByAccount) {
      println("Account %3s -> %3.2f".format(account._1, account._2))
    }
  }
}
