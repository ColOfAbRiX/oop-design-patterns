package com.colofabrix.scala.designpatterns.observer

import com.colofabrix.scala.designpatterns.model._
import wvlet.log.LogSupport

class TransactionLogger extends Subscriber[Transaction] with LogSupport {

  def onNotify(publisher: Publisher[Transaction], transaction: Transaction) {
    logger.info(s"Received transaction $transaction from publisher $publisher")
  }

}
