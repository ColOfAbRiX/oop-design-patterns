package com.colofabrix.scala.designpatterns.loaders

import com.colofabrix.scala.designpatterns.service._
import com.colofabrix.scala.designpatterns.filters._
import com.colofabrix.scala.designpatterns.model._
import CalculatorGenerators._
import org.scalatest.prop._
import org.scalacheck.Gen._
import org.scalatest._

class TransactionCalculatorSpec extends WordSpec with PropertyChecks with Matchers {

  "averageByAccount" should {

    "calculate the correct average for each account" when {

      "using filters" in {
        forAll(genTransactionLoader, genTransactionFilter) {
          (loader: TransactionLoader, filter: TransactionFilter) =>
            // Calculating the result
            val calculator = new TransactionCalculator(loader, filter)
            val result = calculator.averageByAccount()

            // Manually computing the expected result
            val transactions = loader.transactions
            val expected = filter
              .applyFilter(transactions)
              .groupBy(_.account)
              .mapValues { acc =>
                acc.map(_.amount).sum / acc.length
              }

            result should contain theSameElementsAs (expected)
        }
      }

    }
  }

}
