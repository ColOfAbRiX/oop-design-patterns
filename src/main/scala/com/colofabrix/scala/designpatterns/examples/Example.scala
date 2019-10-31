package com.colofabrix.scala.designpatterns.examples

import com.colofabrix.scala.designpatterns.service._
import wvlet.log.LogSupport

//  FACTORY METHOD  //

/**
 * Base class with some commodity features for examples
 */
abstract class Example extends LogSupport {
  /**
   * Calculator for transactions. This is the factory method: the method run() uses this
   * but what it actually is will be left to the specific implementation
   */
  def calculator: TransactionCalculator

  /** Name of the example, taken from the class name */
  def name: String = this.getClass.getSimpleName.replace("$", "").trim()

  /** Request the calculations and display the result */
  final def run(): Unit = {
    info(s"Processing ${name.toUpperCase()}")
    println(s"PROCESSING ${name.toUpperCase()}")

    info(s"Calculating averageByAccount")
    val averageByAccount = calculator.averageByAccount

    for (account <- averageByAccount) {
      val msg = "Account %3s -> %3.2f".format(account._1, account._2)

      trace(msg)
      println(msg)
    }
  }
}
