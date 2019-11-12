package com.colofabrix.scala.designpatterns.iterators

//  ITERATOR PATTERN  //

/**
 * Define an interface for objects that are iterable
 */
trait TIterable[A] {
  // Objects that implement TIterable will return an iterator
  def iterator: TIterator[A]
}

/**
 * Defines an iterator
 */
trait TIterator[A] {
  // Gets the next element
  def next(): A
  // Returns true if there is another element that can be returned
  def hasNext(): Boolean
}
