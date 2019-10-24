package com.colofabrix.scala.designpatterns.examples

import com.colofabrix.scala.designpatterns.service._
import com.colofabrix.scala.designpatterns.filters._
import com.colofabrix.scala.designpatterns.loaders._

/**
 * Showing how to use the STRATEGY pattern to choose how to do things
 */
@SuppressWarnings(Array("org.wartremover.warts.PublicInference"))
object Example1 extends Example {
  // Choose the loader
  val loader = new FileTransactionsLoader("transactions.txt")

  // Choose the filter
  val filter = new AmountRangeTransactionFilter(250, 750)

  // Create the calculator
  val calculator = new TransactionCalculator(loader, filter)
}
