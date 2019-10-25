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

  "FileTransactionsLoader" should "load all the transactions" in {
    val result = new FileTransactionsLoader(dummyTransactionsFile).transactions
    result should contain theSameElementsAs (dummyTransactions)
  }

  "FileTransactionsLoader" should "throw an exception if the file is not present" in {
    intercept[Exception] {
      new FileTransactionsLoader("i-do-not-exist.txt").transactions
    }
  }

}
