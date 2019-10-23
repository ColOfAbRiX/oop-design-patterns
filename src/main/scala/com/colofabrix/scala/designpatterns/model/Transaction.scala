package com.colofabrix.scala.designpatterns.model

final case class Transaction(
    transactionId: String,
    accountId: String,
    transactionDay: Int,
    category: String,
    transactionAmount: Double
) {
  override def toString(): String =
    "Transaction(ID=%s, Account=%3s, Day=%02d, Category=%s, Amount=%3.2f)".format(
      transactionId,
      accountId,
      transactionDay,
      category,
      transactionAmount
    )
}
