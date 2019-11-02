package com.colofabrix.scala.designpatterns.iterators

//  ITERATOR PATTERN  //

/**
 * Define an interface for objects that are iterable
 */
trait TIterable[A] {
  def iterator: TIterator[A]
}

/**
 * Defines an iterator
 */
trait TIterator[A] {
  def next(): A
  def hasNext(): Boolean
}
