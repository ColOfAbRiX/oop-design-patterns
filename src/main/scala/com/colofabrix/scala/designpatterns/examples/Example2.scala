package com.colofabrix.scala.designpatterns.examples

import com.colofabrix.scala.designpatterns.service._
import com.colofabrix.scala.designpatterns.filters._
import com.colofabrix.scala.designpatterns.loaders._
import com.colofabrix.scala.designpatterns._

/**
 * Showing how to use a builder to provide flexibility at configuration
 */
@SuppressWarnings(Array("org.wartremover.warts.PublicInference"))
object Example2 {
  // All the configuration of the TransactionCalculator is done using the builder
  val calcBuilder = TransactionCalculatorBuilder()
    .withFilter(new DayTransactionFilter(8))
    .withLoader(new RandomTransactionsLoader())

  val calc = calcBuilder.build()

  def run(): Unit = {
    println("EXAMPLE 2")
    for (account <- calc.averageByAccount) {
      println("Account %3s -> %3.2f".format(account._1, account._2))
    }
  }
}
