package com.colofabrix.scala.designpatterns.filters

import com.colofabrix.scala.designpatterns.model._

//  DECORATOR PATTERN  //

/**
 * All classes that add behaviour to TransactionFilter will extend this one
 */
abstract class DecoratedTransactionFilter
    extends TransactionFilter

/**
 * This new class composes two filters together
 */
class DoubleTransactionFilter(filter1: TransactionFilter, filter2: TransactionFilter)
    extends DecoratedTransactionFilter {

  def applyFilter(transactions: List[Transaction]): List[Transaction] = {
    val intermediate = filter1.applyFilter(transactions)
    filter2.applyFilter(intermediate)
  }
}

/**
 * This new class add behaviour to existing TransactionFilter by composing many filters
 */
class MultipleTransactionFilter(filters: TransactionFilter*)
    extends DecoratedTransactionFilter {

  def applyFilter(transactions: List[Transaction]): List[Transaction] = {
    filters.foldLeft(transactions) {
      case (intermediate, currentFilter) => currentFilter.applyFilter(intermediate)
    }
  }
}
