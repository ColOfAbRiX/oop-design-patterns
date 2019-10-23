package com.colofabrix.scala.designpatterns.filters

import com.colofabrix.scala.designpatterns.model._

//  ADAPTER (OR WRAPPER) PATTERN  //

/**
 * Adapter to transform a GenericTransactionListFilter to a TransactionFilter
 */
class GenericToFilterAdapter(gtlFilter: GenericTransactionListFilter) extends TransactionFilter {

  def applyFilter(transactions: List[Transaction]): List[Transaction] = {
    // Convert from filtering condition to actually applying the filter to a list
    transactions.filter(gtlFilter.condition)
  }

}
