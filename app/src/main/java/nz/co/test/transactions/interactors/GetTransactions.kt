package nz.co.test.transactions.interactors

import nz.co.test.transactions.framework.TransactionRepository
import javax.inject.Inject

class GetTransactions @Inject constructor (private val transactionRepository: TransactionRepository) {
    suspend operator fun invoke() =
        transactionRepository.getTransactionsList()
}