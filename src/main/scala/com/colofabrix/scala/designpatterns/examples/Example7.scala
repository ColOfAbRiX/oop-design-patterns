package com.colofabrix.scala.designpatterns.examples

import com.colofabrix.scala.designpatterns._
import com.colofabrix.scala.designpatterns.filters._
import com.colofabrix.scala.designpatterns.loaders._
import com.colofabrix.scala.designpatterns.service._
import com.colofabrix.scala.designpatterns.iterators._

/**
 * Showing how to use the ITERABLE pattern to reads a transaction from console
 */
@SuppressWarnings(Array("org.wartremover.warts.PublicInference"))
object Example7 extends Example {

  // We ask the user only if configured, to avoid annoying delays
  val useConsole = GlobalConfig().other.useConsole

  // Create the new console iterable
  val consoleIterable = new ConsoleIterable()
  val iterableLoader = new IterableTransactionLoader(consoleIterable)

  val calcBuilder = TransactionCalculatorBuilder()

  val calculator = if (useConsole) {
    calcBuilder.withLoader(iterableLoader).build()
  } else {
    calcBuilder.build()
  }
}
