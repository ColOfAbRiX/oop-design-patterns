package com.colofabrix.scala.designpatterns.loaders

import java.nio.file._

import com.colofabrix.scala.designpatterns.model._

import scala.util._

//  PROXY PATTERN  //

/**
 * This class control the FileTransactionsLoader safety by performing checks and handling error
 * cases
 */
class SafeTransactionLoader(filePath: String) extends TransactionLoader {
  // We create a loader the way we need and want
  protected def fileLoader: Option[FileTransactionsLoader] = {
    val tmpPath = Paths.get(filePath)

    // Create a new file loader only if it's safe to do so
    if (Files.exists(tmpPath) && Files.isReadable(tmpPath)) {
      Some(new FileTransactionsLoader(filePath))
    } else {
      None
    }
  }

  def transactions: List[Transaction] = {
    // Showing off some FP too :)
    val maybeResult = for {
      safeLoader   <- fileLoader
      transactions <- Try(safeLoader.transactions).toOption
    } yield {
      transactions
    }

    // If there is an issue (and maybeResult is None) we return an empty list
    maybeResult.getOrElse(List.empty[Transaction])
  }
}
