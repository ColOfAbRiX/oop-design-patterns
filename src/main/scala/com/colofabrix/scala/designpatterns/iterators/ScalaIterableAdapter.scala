package com.colofabrix.scala.designpatterns.iterators

//  ITERATOR + ADAPTER PATTERN  //

/**
 * Adapter that transforms Scala Iterables into this project TIterables
 */
class ScalaIterableAdapter[A](iterable: Iterable[A]) extends TIterable[A] {

  class ScalaIteratorAdapter[A](iterator: Iterator[A]) extends TIterator[A] {
    def next(): A = iterator.next()
    def hasNext(): Boolean = iterator.hasNext
  }

  def iterator: TIterator[A] = new ScalaIteratorAdapter(iterable.iterator)

}
