package com.colofabrix.scala.designpatterns.loaders

import com.colofabrix.scala.designpatterns.filters._
import com.colofabrix.scala.designpatterns.model._
import org.scalatest._

class TransactionLoaderSpec extends FlatSpec with Matchers {

  val dummyTransactionsFile = getClass.getResource("/dummy-transactions.txt").getFile
  val dummyTransactions: List[Transaction] = List(
    Transaction("T0008", "A49", 1, "DD", 848.90),
    Transaction("T00012", "A2", 1, "DD", 775.37),
    Transaction("T00044", "A1", 1, "DD", 227.64),
    Transaction("T000134", "A1", 4, "FF", 977.98),
    Transaction("T000199", "A1", 5, "CC", 171.19),
    Transaction("T000271", "A1", 7, "FF", 622.43),
    Transaction("T000164", "A2", 4, "DD", 208.19)
  )
  val badTransactionsFile = getClass.getResource("/bad-transactions.txt").getFile

  // FileTransactionsLoader

  "FileTransactionsLoader" should "load all the transactions" in {
    val result = new FileTransactionsLoader(dummyTransactionsFile).transactions
    result should contain theSameElementsAs (dummyTransactions)
  }

  "FileTransactionsLoader" should "throw an exception if the file is not present" in {
    intercept[Exception] {
      new FileTransactionsLoader("i-do-not-exist.txt").transactions
    }
  }

  // SafeTransactionLoader

  "SafeTransactionLoader" should "load all the transactions" in {
    val result = new SafeTransactionLoader(dummyTransactionsFile).transactions
    result should contain theSameElementsAs (dummyTransactions)
  }

  "SafeTransactionLoader" should "return an empty list when given a missing file" in {
    val result = new SafeTransactionLoader("i-do-not-exist.txt").transactions
    result should have length 0
  }

  "SafeTransactionLoader" should "return an empty list when given a bad file" in {
    val result = new SafeTransactionLoader(badTransactionsFile).transactions
    result should have length 0
  }
}
