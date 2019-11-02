package com.colofabrix.scala.designpatterns.loaders

import com.colofabrix.scala.designpatterns._
import com.colofabrix.scala.designpatterns.model._
import com.colofabrix.scala.designpatterns.iterators._

import scala.io.Source
import scala.collection.mutable.ArraySeq

//  STRATEGY PATTERN  //

/**
 * Represents loader of transaction
 */
trait TransactionLoader {
  /** The full list of transactions */
  def transactions: List[Transaction]
}

object TransactionLoader {
  // Convert a configuration into a loader
  def apply(loader: LoaderType): TransactionLoader = loader match {
    case EmptyLoaderType          => new EmptyTransactionsLoader()
    case FileLoaderType(filePath) => new FileTransactionsLoader(filePath)
    case RandomLoaderType         => new RandomTransactionsLoader()
  }
}

/**
 * Dummy loader that always returns an empty list
 */
class EmptyTransactionsLoader extends TransactionLoader {
  def transactions: List[Transaction] = List.empty[Transaction]
}

/**
 * Loader used for unit testing, always returns the given list
 */
class PresetTransactionsLoader(trs: List[Transaction]) extends TransactionLoader {
  def transactions: List[Transaction] = trs
}

/**
 * Loads transactions from a file
 */
class FileTransactionsLoader(filePath: String) extends TransactionLoader {
  private val transactionsLines = {
    Source.fromFile(filePath)
      .getLines()
      .drop(1)
  }

  val transactions: List[Transaction] = {
    transactionsLines
    .map { line =>
      val split = line.split(',')
      Transaction(split(0), split(1), split(2).toInt, split(3), split(4).toDouble)
    }.toList
  }
}

/**
 * Loads a list of transactions generated randomly
 */
class RandomTransactionsLoader extends TransactionLoader {
  private val rnd = new scala.util.Random(System.currentTimeMillis)

  // List of allowed accounts
  private val accountsList = List.tabulate(10)(i => s"A${i.toString}")

  // List of allowed days
  private val daysList = List.range(1, 60)

  // List of allowed categories
  private val categoriesList = "AA" :: "BB" :: "CC" :: "DD" :: "EE" :: "FF" :: Nil

  // Number of transactions to create
  private val transactionsCount = 1000 + rnd.nextInt() % 1000

  /** Picks a random element inside a given list */
  private def rndElem[A](xs: List[A]): A = xs(rnd.nextInt(xs.length))

  val transactions = List.tabulate(transactionsCount) { transactionId =>
    Transaction(
      "T%05d".format(transactionId),
      rndElem(accountsList),
      rndElem(daysList),
      rndElem(categoriesList),
      rnd.nextFloat() * 1000.0
    )
  }
}
