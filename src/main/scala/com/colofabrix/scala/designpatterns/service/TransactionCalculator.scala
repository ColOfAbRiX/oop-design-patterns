package com.colofabrix.scala.designpatterns.service

import com.colofabrix.scala.designpatterns.filters._
import com.colofabrix.scala.designpatterns.loaders._
import com.colofabrix.scala.designpatterns.model._

//  STRATEGY PATTERN  //

class TransactionCalculator(reader: TransactionLoader, filter: TransactionFilter) {

  /** Commodity method that returns the most updated filtered list of transactions */
  private def getTransactions(): List[Transaction] = {
    // Get the list of transactions
    val transactions = reader.transactions
    // Apply the filter and return the result
    filter.applyFilter(transactions)
  }

  private def averageAmounts(transactions: List[Transaction]): Double = {
    transactions.map(_.amount).sum / transactions.length
  }

  //  HOW TO WORK ON DATA WITH SCALA  //

  def averageByAccount(): Map[String, Double] = {
    this.getTransactions()
      // Group transactions by Account ID
      .groupBy(_.account)
      // Remove accounts that don't have transactions (just to show one more step)
      .filter(_._2.length > 0)
      // For each account...
      .mapValues(averageAmounts)
      .toMap
  }

  def averageByAccountByDay(): Map[(String, Int), Double] = {
    this.getTransactions()
      .groupBy(t => (t.account, t.day))
      .mapValues(averageAmounts)
      .toMap
  }

}
