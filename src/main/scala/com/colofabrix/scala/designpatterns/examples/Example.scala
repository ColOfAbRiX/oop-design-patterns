package com.colofabrix.scala.designpatterns.examples

import com.colofabrix.scala.designpatterns.service._
import com.colofabrix.scala.designpatterns.filters._
import com.colofabrix.scala.designpatterns.loaders._
import wvlet.log.LogSupport

//  FACTORY METHOD  //

/**
 * Base class with some commodity features for examples
 */
abstract class Example extends LogSupport {
  /** Calculator for transactions (this is the factory method) */
  def calculator: TransactionCalculator

  /** Name of the example, taken from the class name */
  def name: String = this.getClass.getSimpleName.replace("$", "")

  /** Request the calculations and display the result */
  final def run(): Unit = {
    println(s"PROCESSING ${name.toUpperCase()}")
    info(s"Processing ${name.toUpperCase()}")

    val averageByAccount = calculator.averageByAccount
    info(s"Calculating averageByAccount")

    for (account <- averageByAccount) {
      val msg = "Account %3s -> %3.2f".format(account._1, account._2)

      println(msg)
      trace(msg)
    }
  }
}
