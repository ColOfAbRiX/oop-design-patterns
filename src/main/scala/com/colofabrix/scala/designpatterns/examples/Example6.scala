package com.colofabrix.scala.designpatterns.examples

import com.colofabrix.scala.designpatterns.service._
import com.colofabrix.scala.designpatterns.filters._
import com.colofabrix.scala.designpatterns.loaders._
import com.colofabrix.scala.designpatterns._

/**
 * Showing how to control access to an existing objects using a PROXY pattern
 * This pattern looks very similar to the decorator but its focus is different
 */
@SuppressWarnings(Array("org.wartremover.warts.PublicInference"))
object Example6 extends Example {
  // Create a new loader building around an existing loader
  val loader = new CachedTransactionLoader(
    new LoggedTransactionLoader(
      TransactionLoader(GlobalConfig().loaderType)
    )
  )

  val filter = TransactionFilter(GlobalConfig().filterType)

  // Let's prove that the requests are indeed cached making 10 calls
  for( _ <- 0 until 10 ) loader.transactions

  // Create the calculator
  val calculator = new TransactionCalculator(loader, filter)
}
