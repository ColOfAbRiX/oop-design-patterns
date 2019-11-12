package com.colofabrix.scala.designpatterns.examples

import com.colofabrix.scala.designpatterns.filters._
import com.colofabrix.scala.designpatterns.loaders._
import com.colofabrix.scala.designpatterns.service._
import com.colofabrix.scala.designpatterns.model._

/**
 * Showing how the COMPOSITE patterns allows to building nested recursive structures
 */
@SuppressWarnings(Array("org.wartremover.warts.PublicInference"))
object Example8 extends Example {
  // Choose the loader
  val loader = new FileTransactionsLoader("transactions.txt")

    // This is to show how to create an anonymous type in Scala that is created inside an anonymous
    // function. It's effectively a "on the spot" new type of filter
    val accountFilter = { account: String =>
      new TransactionFilter {
        def applyFilter(tr: List[Transaction]) = tr.filter(_.account == account)
      }
    }

  // Create a complex filter by aggregating several other filters
  val filter = new AndComplexFilter(
    // Select one single account
    accountFilter("A5"),
    // Select the amount range
    new AmountRangeTransactionFilter(200, 600),
    // Select transactions made on either of two specific days
    new OrComplexFilter(
      new DayTransactionFilter(1),
      new DayTransactionFilter(7),
      new DayTransactionFilter(11),
    )
  )

  // Create the calculator
  val calculator = new TransactionCalculator(loader, filter)
}
