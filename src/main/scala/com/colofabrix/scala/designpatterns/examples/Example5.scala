package com.colofabrix.scala.designpatterns.examples

import com.colofabrix.scala.designpatterns.GlobalConfig
import com.colofabrix.scala.designpatterns.service._
import com.colofabrix.scala.designpatterns.filters._
import com.colofabrix.scala.designpatterns.loaders._

/**
 * Showing how to use a SINGLETON pattern to obtain a configuration and how to use a configuration
 * to allow for dynamic behaviour set by the final user
 */
@SuppressWarnings(Array("org.wartremover.warts.PublicInference"))
object Example5 extends Example {
  // Initialize everything automatically
  val loader = TransactionLoader(GlobalConfig().loaderType)
  val filter = TransactionFilter(GlobalConfig().filterType)
  val calculator = new TransactionCalculator(loader, filter)
}
