package com.colofabrix.scala.designpatterns.filters

import com.colofabrix.scala.designpatterns._
import com.colofabrix.scala.designpatterns.model._

//  COMPOSITE PATTERN  //

/**
 * This new class composes filters together and given it is itself a TransactionFilter you can nest
 * several of them so to create a tree-like structure
 */
class AndComplexFilter(filters: TransactionFilter*) extends TransactionFilter {
  def applyFilter(transactions: List[Transaction]): List[Transaction] = {
    filters.foldLeft(transactions) {
      case (intermediate, currentFilter) =>
        currentFilter.applyFilter(intermediate)
    }
  }
}

/**
 * This new class composes filters together and given it is itself a TransactionFilter you can nest
 * several of them so to create a tree-like structure
 */
class OrComplexFilter(filters: TransactionFilter*) extends TransactionFilter {
  def applyFilter(transactions: List[Transaction]): List[Transaction] = {
    filters
      .map(_.applyFilter(transactions))
      .flatten
      .toList
  }
}
