package com.colofabrix.scala.designpatterns.examples

import com.colofabrix.scala.designpatterns.service._
import com.colofabrix.scala.designpatterns.filters._
import com.colofabrix.scala.designpatterns.loaders._
import com.typesafe.scalalogging.LazyLogging

//  FACTORY METHOD  //

/**
 * Base class with some commodity features for examples
 */
abstract class Example extends LazyLogging {
  /** Name of the example */
  def name: String

  /** Calculator for transactions (this is the factory method) */
  def calculator: TransactionCalculator

  /** Request the calculations and display the result */
  def run(): Unit = {
    println(s"PROCESSING ${name.toUpperCase()}")
    logger.info(s"Processing ${name.toUpperCase()}")

    val averageByAccount = calculator.averageByAccount
    logger.info(s"Calculating averageByAccount")

    for (account <- averageByAccount) {
      println("Account %3s -> %3.2f".format(account._1, account._2))
      logger.debug("Account %3s -> %3.2f".format(account._1, account._2))
    }
  }
}
