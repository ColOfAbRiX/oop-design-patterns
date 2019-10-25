package com.colofabrix.scala.designpatterns.filters

import com.colofabrix.scala.designpatterns.model._

//  DECORATOR PATTERN  //

/**
 * This new class composes two filters together
 */
class DoubleTransactionFilter(filter1: TransactionFilter, filter2: TransactionFilter)
    extends TransactionFilter {

  def applyFilter(transactions: List[Transaction]): List[Transaction] = {
    val intermediate = filter1.applyFilter(transactions)
    filter2.applyFilter(intermediate)
  }
}

/**
 * This new class add behaviour to existing TransactionFilter by composing many filters
 */
class MultipleTransactionFilter(filters: TransactionFilter*) extends TransactionFilter {
  def applyFilter(transactions: List[Transaction]): List[Transaction] = {
    filters.foldLeft(transactions) {
      case (intermediate, currentFilter) => currentFilter.applyFilter(intermediate)
    }
  }
}
