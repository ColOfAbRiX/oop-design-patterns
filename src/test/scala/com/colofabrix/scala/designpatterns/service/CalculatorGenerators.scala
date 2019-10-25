package com.colofabrix.scala.designpatterns.loaders

import com.colofabrix.scala.designpatterns.service._
import com.colofabrix.scala.designpatterns.filters._
import com.colofabrix.scala.designpatterns.model._
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Gen._
import org.scalacheck._

object CalculatorGenerators {

  // Transaction

  /** Generates one random transaction */
  def genTransaction: Gen[Transaction] = for {
    id       <- arbitrary[String]
    account  <- arbitrary[String]
    day      <- arbitrary[Int]
    category <- arbitrary[String]
    amount   <- arbitrary[Double]
  } yield {
    Transaction(id, account, day, category, amount)
  }

  /** Generates a list of transactions */
  def genTransactions: Gen[List[Transaction]] = listOf(genTransaction)

  // TransactionLoader

  /** Generates a Loader with random transactions */
  def genTransactionLoader: Gen[TransactionLoader] = for {
    transactions <- genTransactions
  } yield {
    new PresetTransactionsLoader(transactions)
  }

  // TransactionFilter

  /** Generates noop filter */
  def genNoopFiler: Gen[NoopTransactionFilter] = const(new NoopTransactionFilter())

  /** Generates day filter */
  def genDayFilter: Gen[DayTransactionFilter] = for {
    day <- arbitrary[Int] if day > 0
  } yield {
    new DayTransactionFilter(day)
  }

  /** Generates amount range filter */
  def genAmountRangeFilter: Gen[AmountRangeTransactionFilter] = for {
    min <- arbitrary[Double] if min >= 0.0 && min < 500.0
    max <- arbitrary[Double] if max > min
  } yield {
    new AmountRangeTransactionFilter(min, max)
  }

  /** Generates a transaction filter */
  def genTransactionFilter: Gen[TransactionFilter] = {
    oneOf(genNoopFiler, genDayFilter, genAmountRangeFilter)
  }

  // TransactionCalculator

  /** Generates a transaction calculator */
  def genTransactionCalculator: Gen[TransactionCalculator] = for {
    loader <- genTransactionLoader
    filter <- genTransactionFilter
  } yield {
    new TransactionCalculator(loader, filter)
  }

}
