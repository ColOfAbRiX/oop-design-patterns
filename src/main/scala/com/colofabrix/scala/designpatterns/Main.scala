package com.colofabrix.scala.designpatterns

import com.colofabrix.scala.designpatterns.examples._

object TransactionsExercise extends App {
  Logging.init()

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
