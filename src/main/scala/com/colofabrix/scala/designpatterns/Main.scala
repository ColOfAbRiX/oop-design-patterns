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

  // Singleton pattern + Config
  Example4.run()

  // Proxy pattern
  Example5.run()

  // Decorator pattern + Logging
  Example6.run()

  // Iterator pattern
  Example7.run()

  // Composite pattern
  Example8.run()
}
