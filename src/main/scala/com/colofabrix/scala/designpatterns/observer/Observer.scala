package com.colofabrix.scala.designpatterns.observer

import scala.collection._

//  OBSERVER PATTERN  //

/**
 * Implements the interface of a publisher
 */
trait Publisher[A] {
  private val subscribers: mutable.ListBuffer[Subscriber[A]] = mutable.ListBuffer()

  /** Adds a subscriber to the notification list */
  final def subscribe(subscriber: Subscriber[A]): Unit = {
    if (!subscribers.contains(subscriber)) {
      subscribers += subscriber
    }
  }

  /** Removes a subscriber to the notification list */
  final def unsubscribe(subscriber: Subscriber[A]): Unit = {
    if (subscribers.contains(subscriber)) {
      subscribers -= subscriber
    }
  }

  /** Notifies all subscribers sending them a value */
  final protected def notify(value: A): Unit = {
    subscribers.foreach(_.onNotify(this, value))
  }
}

/**
 * A subscriber that can receive notifications
 */
trait Subscriber[A] {
  def onNotify(publisher: Publisher[A], value: A): Unit
}
