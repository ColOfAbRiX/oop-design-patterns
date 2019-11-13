package com.colofabrix.scala.designpatterns.examples

import com.colofabrix.scala.designpatterns.iterators.ScalaIterableAdapter
import com.colofabrix.scala.designpatterns.filters._
import com.colofabrix.scala.designpatterns.loaders._
import com.colofabrix.scala.designpatterns.service._
import com.colofabrix.scala.designpatterns.observer.TransactionLogger

/**
 * Uses the OBSERVER pattern to notify objects of new transactions in the list
 */
@SuppressWarnings(Array("org.wartremover.warts.PublicInference"))
object Example9 extends Example {
  // Choose the loader
  val loader = new FileTransactionsLoader("transactions.txt")

  // Create an object that "emits" transactions to the subscribers. For this exercise we want to
  // use List[Transaction] as our custom Iterable
  val transactionEmitter = new IterablePublisher(
    new ScalaIterableAdapter(loader.transactions)
  )
  // Create the publishers and register them with the emitter
  val transactionLogger = new TransactionLogger
  transactionEmitter.subscribe(transactionLogger)

  // Start the emission!
  transactionEmitter.start()

  // In this exercise we don't care about these
  val filter = new AmountRangeTransactionFilter(250, 750)
  val calculator = new TransactionCalculator(loader, filter)
}
