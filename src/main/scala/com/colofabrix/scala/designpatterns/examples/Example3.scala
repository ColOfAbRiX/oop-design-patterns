package com.colofabrix.scala.designpatterns.examples

import com.colofabrix.scala.designpatterns.filters._

/**
 * Showing how to use an ADAPTER pattern to adapt the object we have available with a different
 * object that is required to be passed
 */
@SuppressWarnings(Array("org.wartremover.warts.PublicInference"))
object Example3 extends Example {
  // Build a different type of filter
  val filter = GenericTransactionListFilter.dayFilter(List(), 8)

  // And then wrap it inside a FilterAdapter
  val adaptedFilter = new GenericToFilterAdapter(filter)

  // Reuse the TransactionCalculatorBuilder we set up before in Example2
  val calculator = Example2.calcBuilder
    .withFilter(adaptedFilter)
    .build()
}
