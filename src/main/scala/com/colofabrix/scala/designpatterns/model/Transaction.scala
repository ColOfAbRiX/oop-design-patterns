package com.colofabrix.scala.designpatterns.model

final case class Transaction(
    id: String,
    account: String,
    day: Int,
    category: String,
    amount: Double
) {
  override def toString(): String =
    "Transaction(ID=%s, Account=%3s, Day=%02d, Category=%s, Amount=%3.2f)"
      .format(id, account, day, category, amount)
}
