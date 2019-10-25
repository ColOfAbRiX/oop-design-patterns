package com.colofabrix.scala.designpatterns.filters

import com.colofabrix.scala.designpatterns.model._
import com.colofabrix.scala.designpatterns.filters._
import org.scalatest._

class TransactionFilterSpec extends FlatSpec with Matchers {

  val emptyTransactionList: List[Transaction] = List.empty[Transaction]

  val nonEmptyTransactionList: List[Transaction] = List(
    Transaction("T0008", "A49", 1, "DD", 848.90),
    Transaction("T00012", "A2", 1, "DD", 775.37),
    Transaction("T00044", "A1", 1, "DD", 227.64),
    Transaction("T000134", "A1", 4, "FF", 977.98),
    Transaction("T000199", "A1", 5, "CC", 171.19),
    Transaction("T000271", "A1", 7, "FF", 622.43),
    Transaction("T000164", "A2", 4, "DD", 208.19)
  )

  // NoopTransactionFilter

  "NoopTransactionFilter" should "returns all the transactions" in {
    val filtered = new NoopTransactionFilter()
      .applyFilter(nonEmptyTransactionList)

    filtered should contain theSameElementsAs (nonEmptyTransactionList)
  }

  "NoopTransactionFilter" should "work on empty lists" in {
    val filtered = new NoopTransactionFilter()
      .applyFilter(emptyTransactionList)

    filtered should equal (emptyTransactionList)
  }

  // DayTransactionFilter

  "DayTransactionFilter" should "filter transactions by day" in {
    val expected: List[Transaction] = List(
      Transaction("T000134", "A1", 4, "FF", 977.98),
      Transaction("T000164", "A2", 4, "DD", 208.19)
    )

    val filtered = new DayTransactionFilter(4)
      .applyFilter(nonEmptyTransactionList)

    filtered should contain theSameElementsAs (expected)
  }

  "DayTransactionFilter" should "work on empty lists" in {
    val filtered = new DayTransactionFilter(5)
      .applyFilter(emptyTransactionList)

    filtered should equal (emptyTransactionList)
  }

  // AmountRangeTransactionFilter

  "AmountRangeTransactionFilter" should "filter transactions by amount range" in {
    val expected: List[Transaction] = List(
      Transaction("T00044", "A1", 1, "DD", 227.64),
      Transaction("T000271", "A1", 7, "FF", 622.43),
      Transaction("T000164", "A2", 4, "DD", 208.19)
    )

    val filtered = new AmountRangeTransactionFilter(200.0, 700.0)
      .applyFilter(nonEmptyTransactionList)

    filtered should contain theSameElementsAs (expected)
  }

  "AmountRangeTransactionFilter" should "work on empty lists" in {
    val filtered = new AmountRangeTransactionFilter(200.0, 700.0)
      .applyFilter(emptyTransactionList)

    filtered should equal (emptyTransactionList)
  }

}
