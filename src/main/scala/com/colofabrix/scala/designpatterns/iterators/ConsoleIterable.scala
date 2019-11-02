package com.colofabrix.scala.designpatterns.iterators

import com.colofabrix.scala.designpatterns.model._
import scala.io._

//  ITERATOR PATTERN  //

/**
 * Reads transaction from the console, one line at the time
 */
class ConsoleIterable extends TIterable[Transaction] {

  class ConsoleIterator extends TIterator[Transaction] {
    /** Reads all the fields of a transaction from the console and then builds a Transaction */
    def next(): Transaction = {
      val id = StdIn.readLine("\nID (e.g. T000140): ")
      val account = StdIn.readLine("\nAccount (e.g. A2): ")
      val day = StdIn.readLine("\nDay (e.g. 4): ").toInt
      val category = StdIn.readLine("\nCategory (e.g. BB): ")
      val amount = StdIn.readLine("\nAmount (e.g. 123.45): ").toDouble
      println("")
      Transaction(id, account, day, category, amount)
    }

    /** Asks the console if he or she wants to carry on */
    def hasNext(): Boolean = {
      val continue = StdIn.readLine("Read next transaction? ")
        .toBooleanOption
        .getOrElse(false)
      println("")
      continue
    }
  }

  /** Returns the console iterator */
  def iterator: TIterator[Transaction] = new ConsoleIterator()

}
