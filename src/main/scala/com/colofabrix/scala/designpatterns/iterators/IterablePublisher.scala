package com.colofabrix.scala.designpatterns.loaders

import scala.annotation.tailrec
import com.colofabrix.scala.designpatterns.observer._
import com.colofabrix.scala.designpatterns.iterators._

/**
 * Publisher that notifies subscribers for each element of an TIterable
 */
class IterablePublisher[A](iterable: TIterable[A]) extends Publisher[A] {

  /** Allows to decide when to start walking through the iterator */
  def start(): Unit = {
    this.iterate(this.iterable.iterator)
  }

  @tailrec
  private def iterate(iterator: TIterator[A]): Unit = {
    this.notify(iterator.next())
    if (iterator.hasNext()) {
      iterate(iterator)
    }
  }

}
