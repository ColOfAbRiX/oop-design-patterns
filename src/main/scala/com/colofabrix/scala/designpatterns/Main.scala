package com.colofabrix.scala.designpatterns

import com.colofabrix.scala.designpatterns.examples._
import com.typesafe.scalalogging.LazyLogging

object TransactionsExercise extends App with LazyLogging {
  logger.info("Welcome to TransactionsExercise")

  // Strategy pattern
  Example1.run()

  // Builder pattern
  Example2.run()

  // Adapter pattern
  Example3.run()

  // Decorator pattern
  Example4.run()

  // Singleton pattern + Config
  Example5.run()
}
