package com.colofabrix.scala.designpatterns.loaders

import com.colofabrix.scala.designpatterns.model._
import com.colofabrix.scala.designpatterns._
import wvlet.log.LogSupport

//  PROXY PATTERN  //

/**
 * This class provides logging when we call the methods of a TransactionLoader
 */
class LoggedTransactionLoader(loader: TransactionLoader) extends TransactionLoader with LogSupport {
  info(s"Initialized LoggedLoader around $loader")

  def transactions: List[Transaction] = {
    debug(s"Accessing transactions...")
    loader.transactions
  }
}

/**
 * This class cached the transactions read by the loader
 */
class CachedTransactionLoader(loader: TransactionLoader) extends TransactionLoader {
  private var cachedTransactions: List[Transaction] = loader.transactions

  def transactions: List[Transaction] = {
    this.cachedTransactions
  }

  def refresh(): Unit = {
    this.cachedTransactions = loader.transactions
  }
}
