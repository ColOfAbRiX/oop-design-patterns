package com.colofabrix.scala.designpatterns.examples

import com.colofabrix.scala.designpatterns.filters._
import com.colofabrix.scala.designpatterns.loaders._
import com.colofabrix.scala.designpatterns.service._

/**
 * Showing how to use a BUILDER pattern to provide flexibility at configuration
 */
@SuppressWarnings(Array("org.wartremover.warts.PublicInference"))
object Example2 extends Example {
  // All the configuration of the TransactionCalculator is done using the builder
  val calcBuilder = TransactionCalculatorBuilder()
    .withFilter(new DayTransactionFilter(8))
    .withLoader(new RandomTransactionsLoader())

  val calculator = calcBuilder.build()
}
