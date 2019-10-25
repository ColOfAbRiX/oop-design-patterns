package com.colofabrix.scala.designpatterns.filters

import com.colofabrix.scala.designpatterns.model._

/**
 * A generic filter class for lists that can apply a requested condition to a specified list
 */
class GenericListFilter[A](
    val list: List[A],
    val condition: A => Boolean
) {
  /** Gets the filtered list */
  def getFilteredList(): List[A] = list.filter(condition)
}

/**
 * Specializes the GenericListFilter for Transaction objects
 */
class GenericTransactionListFilter(trs: List[Transaction], condition: Transaction => Boolean)
    extends GenericListFilter[Transaction](trs, condition)

object GenericTransactionListFilter {

  // Builder of oa no-op filter
  def noopFilter(trs: List[Transaction]): GenericTransactionListFilter =
    new GenericTransactionListFilter(trs, x => true)

  // Builder of a filter by day
  def dayFilter(trs: List[Transaction], day: Int): GenericTransactionListFilter =
    new GenericTransactionListFilter(trs, x => x.day == day)

  // Builder of a filter by amount range
  def amountRangeFilter(
      trs: List[Transaction],
      min: Double,
      max: Double
  ): GenericTransactionListFilter =
    new GenericTransactionListFilter(
      trs,
      x => x.amount >= min && x.amount <= max
    )

}
