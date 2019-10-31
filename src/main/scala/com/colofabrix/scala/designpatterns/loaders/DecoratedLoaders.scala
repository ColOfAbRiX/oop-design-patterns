package com.colofabrix.scala.designpatterns.loaders

import com.colofabrix.scala.designpatterns.model._
import wvlet.log.{LogLevel, LogSupport}

//  DECORATOR PATTERN  //

/**
 * Base class for DecoratedTransactionLoader and used to provide common data to the children
 */
abstract class DecoratedTransactionLoader(loader: TransactionLoader) extends TransactionLoader

/**
 * This class provides logging when of what we do with it
 */
class LoggedTransactionLoader(loader: TransactionLoader)
    extends DecoratedTransactionLoader(loader)
    with LogSupport {

  info(s"Started LoggedTransactionLoader using loader '${loader.getClass.getSimpleName}'")

  def transactions: List[Transaction] = {
    info("Loading transactions...")
    val result = loader.transactions

    // Doing something more when TRACE is enabled
    if( logger.isEnabled(LogLevel.TRACE) ) {
      trace(s"List of loaded transactions")
      result.foreach(trace(_))
    }

    debug(s"Found ${result.length} transactions")
    result
  }
}

/**
 * This class caches the transactions read by any Loader
 */
class CachedTransactionLoader(loader: TransactionLoader)
    extends DecoratedTransactionLoader(loader) {

  // Read the transactions once
  private var cachedTransactions: List[Transaction] = loader.transactions

  def transactions: List[Transaction] = this.cachedTransactions

  /** Refresh the cached transactions */
  def reload(): Unit = {
    this.cachedTransactions = loader.transactions
  }
}
