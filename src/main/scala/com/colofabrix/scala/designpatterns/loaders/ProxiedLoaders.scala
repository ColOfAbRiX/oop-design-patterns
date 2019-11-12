package com.colofabrix.scala.designpatterns.loaders

import com.colofabrix.scala.designpatterns.model._
import wvlet.log.LogSupport
import java.nio.file._
import scala.util._
import java.io._

//  PROXY PATTERN  //

/**
 * This class control the FileTransactionsLoader safety by performing checks and handling error
 * cases. Showing off some FP too :)
 */
class SafeTransactionLoader(filePath: String) extends TransactionLoader with LogSupport {
  // We create a loader the way we need and want, wrapping around FileTransactionsLoader
  protected def fileLoader: Try[FileTransactionsLoader] = {
    val tmpPath = Paths.get(filePath)

    if (Files.exists(tmpPath) && Files.isReadable(tmpPath)) {
      // Create a new file loader only if it's safe to do so
      logger.info(s"Creating FileTransactionsLoader from file $tmpPath")
      Try(new FileTransactionsLoader(filePath))
    }
    else {
      // In case of problems we return a failure
      val msg = s"The provided file $filePath doesn't exists or it's not readable"
      logger.error(msg)
      Failure[FileTransactionsLoader](new IOException(msg))
    }
  }

  @SuppressWarnings(Array("org.wartremover.warts.TryPartial"))
  def transactions: List[Transaction] = {
    val safeResult = for {
      safeLoader   <- fileLoader
      transactions <- Try(safeLoader.transactions)
    } yield {
      transactions
    }

    // If there is an issue (and safeResult is Left) we return an empty list
    safeResult
      .recover {
        case exception =>
          logger.error(s"Couldn't load transactions: ${exception.getMessage}")
          List.empty[Transaction]
      }
      .get
  }
}
