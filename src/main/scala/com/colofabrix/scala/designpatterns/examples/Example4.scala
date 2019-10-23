package com.colofabrix.scala.designpatterns.examples

import com.colofabrix.scala.designpatterns.service._
import com.colofabrix.scala.designpatterns.filters._
import com.colofabrix.scala.designpatterns.loaders._
import com.colofabrix.scala.designpatterns._

/**
 * Showing how to enrich existing objects by wrapping them
 */
@SuppressWarnings(Array("org.wartremover.warts.PublicInference"))
object Example4 {
  val loader = new FileTransactionsLoader("transactions.txt")

  // Create a new filter building around existing filters
  val filter = new DoubleTransactionFilter(Example1.filter, Example3.adaptedFilter)

  // Create the calculator
  val calc = new TransactionCalculator(loader, filter)

  def run(): Unit = {
    println("EXAMPLE 4")
    for (account <- calc.averageByAccount) {
      println("Account %3s -> %3.2f".format(account._1, account._2))
    }
  }
}
