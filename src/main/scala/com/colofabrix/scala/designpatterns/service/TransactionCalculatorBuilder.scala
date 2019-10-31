package com.colofabrix.scala.designpatterns.service

import com.colofabrix.scala.designpatterns.filters._
import com.colofabrix.scala.designpatterns.loaders._

//  BUILDER PATTERN  //

/**
 * Builder forTransactionCalculator
 */
class TransactionCalculatorBuilder private (
    val loader: TransactionLoader,
    val filter: TransactionFilter
) {

  /** Builds a new TransactionCalculator */
  def build(): TransactionCalculator = {
    new TransactionCalculator(this.loader, this.filter)
  }

  /** Sets the loader to user */
  def withLoader(loader: TransactionLoader): TransactionCalculatorBuilder = {
    new TransactionCalculatorBuilder(loader, this.filter)
  }

  /** Sets the filter to use */
  def withFilter(filter: TransactionFilter): TransactionCalculatorBuilder = {
    new TransactionCalculatorBuilder(this.loader, filter)
  }

  /** Use the default loader */
  def defaultLoader(): TransactionCalculatorBuilder = {
    new TransactionCalculatorBuilder(new EmptyTransactionsLoader(), filter)
  }

  /** Use the default loader */
  def defaultFilter(): TransactionCalculatorBuilder = {
    new TransactionCalculatorBuilder(this.loader, new NoopTransactionFilter())
  }

}

object TransactionCalculatorBuilder {

  /** Initializes the Builder with the default loader and filter */
  def apply(): TransactionCalculatorBuilder = {
    new TransactionCalculatorBuilder(new EmptyTransactionsLoader(), new NoopTransactionFilter())
  }

}
