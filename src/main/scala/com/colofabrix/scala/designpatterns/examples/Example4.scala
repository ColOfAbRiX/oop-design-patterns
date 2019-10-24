package com.colofabrix.scala.designpatterns.examples

import com.colofabrix.scala.designpatterns.service._
import com.colofabrix.scala.designpatterns.filters._
import com.colofabrix.scala.designpatterns.loaders._

/**
 * Showing how to enrich existing objects using a DECORATOR pattern
 */
@SuppressWarnings(Array("org.wartremover.warts.PublicInference"))
object Example4 extends Example {
  val loader = new FileTransactionsLoader("transactions.txt")

  // Create a new filter building around existing filters
  val filter = new DoubleTransactionFilter(Example1.filter, Example3.adaptedFilter)

  // Create the calculator
  val calculator = new TransactionCalculator(loader, filter)
}
