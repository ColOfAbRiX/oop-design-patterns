package com.colofabrix.scala.designpatterns.loaders

import com.colofabrix.scala.designpatterns.model._
import com.colofabrix.scala.designpatterns.iterators._

/**
 * Loader that works on any Iterable object
 */
class IterableTransactionLoader(iterable: TIterable[Transaction]) extends TransactionLoader {
  def transactions: List[Transaction] = {
    val iterator = iterable.iterator
    var output = List.empty[Transaction]

    // Load one element at the time
    while (iterator.hasNext ) {
      output = iterator.next() +: output
    }

    output
  }
}
