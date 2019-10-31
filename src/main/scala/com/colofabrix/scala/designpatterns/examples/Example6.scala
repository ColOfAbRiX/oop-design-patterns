package com.colofabrix.scala.designpatterns.examples

import com.colofabrix.scala.designpatterns._
import com.colofabrix.scala.designpatterns.filters._
import com.colofabrix.scala.designpatterns.loaders._
import com.colofabrix.scala.designpatterns.service._

/**
 * Showing how to enrich existing objects using a DECORATOR pattern
 */
@SuppressWarnings(Array("org.wartremover.warts.PublicInference"))
object Example6 extends Example {
  // We create a long series of russian-dolls loaders
  val realLoader = new FileTransactionsLoader("transactions.txt")
  val innerLogger = new LoggedTransactionLoader(realLoader)
  val middleCachedLoader = new CachedTransactionLoader(innerLogger)
  val outerLogger = new LoggedTransactionLoader(middleCachedLoader)

  // Showing that the realLoader is called only once because we cache!
  for( i <- 0 to 5 ) outerLogger.transactions

  // Knowing the exact implementation of a loader we can use its additional "decorated" features
  middleCachedLoader.reload()

  // Filter as usual
  val filter = TransactionFilter(GlobalConfig().filterType)

  // Create the calculator
  val calculator = new TransactionCalculator(outerLogger, filter)
}
