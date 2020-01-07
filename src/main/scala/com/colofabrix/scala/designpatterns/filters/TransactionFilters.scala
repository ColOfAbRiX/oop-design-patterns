package com.colofabrix.scala.designpatterns.filters

import com.colofabrix.scala.designpatterns._
import com.colofabrix.scala.designpatterns.model._

//  STRATEGY PATTERN  //

/**
 * Represent an object that filters transactions
 */
trait TransactionFilter {
  /** Given a list of transaction it returns the filtered list by applying filteredCondition */
  def applyFilter(transactions: List[Transaction]): List[Transaction]
}

object TransactionFilter {
  // Convert a configuration into a filter
  def apply(filter: FilterType): TransactionFilter = filter match {
    case NoopFilterType                  => new NoopTransactionFilter()
    case DayFilterType(day)              => new DayTransactionFilter(day)
    case AmountRangeFilterType(min, max) => new AmountRangeTransactionFilter(min, max)
  }
}

/**
 * Simple, do-nothing filter
 */
class NoopTransactionFilter extends TransactionFilter {
  def applyFilter(transactions: List[Transaction]): List[Transaction] = {
    transactions.filter(_ => true)
  }
}

/**
 * An object that filters transactions made on a specific day
 */
class DayTransactionFilter(day: Int) extends TransactionFilter {
  def applyFilter(transactions: List[Transaction]): List[Transaction] = {
    transactions.filter(_.day == day)
  }
}

/**
 * An object that filters transactions with a value in a range
 */
class AmountRangeTransactionFilter(min: Double, max: Double) extends TransactionFilter {
  def applyFilter(transactions: List[Transaction]): List[Transaction] = {
    transactions.filter(tr => tr.amount >= min && tr.amount < max)
  }
}
