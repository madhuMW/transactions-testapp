package nz.co.test.transactions.interactors

import nz.co.test.transactions.framework.TransactionRepository

class GetTransactions(private val transactionRepository: TransactionRepository) {
    suspend operator fun invoke() =
        transactionRepository.getTransactionsList()
}