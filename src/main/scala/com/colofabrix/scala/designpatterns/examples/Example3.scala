package com.colofabrix.scala.designpatterns.examples

import com.colofabrix.scala.designpatterns.service._
import com.colofabrix.scala.designpatterns.filters._
import com.colofabrix.scala.designpatterns.loaders._
import com.colofabrix.scala.designpatterns._

/**
 * Showing how to adapt a different object to "fit" the space of another, being used as another
 */
@SuppressWarnings(Array("org.wartremover.warts.PublicInference"))
object Example3 {
  // Build a different type of filter
  val filter = GenericTransactionListFilter.dayFilter(List(), 8)

  // And then wrap it inside a FilterAdapter
  val adaptedFilter = new GenericToFilterAdapter(filter)

  // Reuse the TransactionCalculatorBuilder we set up before in Example2
  val calc = Example2.calcBuilder
    .withFilter(adaptedFilter)
    .build()

  def run(): Unit = {
    println("EXAMPLE 3")
    for (account <- calc.averageByAccount) {
      println("Account %3s -> %3.2f".format(account._1, account._2))
    }
  }
}
