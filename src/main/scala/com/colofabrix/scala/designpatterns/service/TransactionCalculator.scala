package com.colofabrix.scala.designpatterns.service

import com.colofabrix.scala.designpatterns.model._
import com.colofabrix.scala.designpatterns.filters._
import com.colofabrix.scala.designpatterns.loaders._

//  STRATEGY PATTERN  //

class TransactionCalculator(reader: TransactionLoader, filter: TransactionFilter) {

  def getTransactions(): List[Transaction] = {
    // Get the list of transactions
    val transactions = reader.transactions
    // Apply the filter and return the result
    filter.applyFilter(transactions)
  }

  //  HOW TO WORK ON DATA WITH SCALA  //

  def averageByAccount(): Map[String, Double] = {
    this.getTransactions()
      // Group transactions by Account ID
      .groupBy(_.accountId)
      // Remove accounts that don't have transactions
      .filter(_._2.length > 0)
      // For each account...
      .mapValues { account =>
        // ...calculate the average amount
        account.map(_.transactionAmount).sum / account.length
      }.toMap
  }

}
