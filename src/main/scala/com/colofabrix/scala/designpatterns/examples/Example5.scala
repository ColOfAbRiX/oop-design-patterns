package com.colofabrix.scala.designpatterns.examples

import com.colofabrix.scala.designpatterns.loaders._
import com.colofabrix.scala.designpatterns.service._

/**
 * Showing how to control access to an existing objects using a PROXY pattern
 * This pattern looks very similar to the decorator but its focus is different
 */
@SuppressWarnings(Array("org.wartremover.warts.PublicInference"))
object Example5 extends Example {
  // Trying to load a file that doesn't exist
  val loader = new SafeTransactionLoader("i_do_not_exist.txt")

  // Create a new filter building around existing filters
  val filter = Example4.filter

  // Create the calculator
  val calculator = new TransactionCalculator(loader, filter)
}
