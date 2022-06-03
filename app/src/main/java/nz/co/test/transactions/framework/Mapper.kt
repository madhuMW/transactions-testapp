package nz.co.test.transactions.framework

import nz.co.test.transactions.data.mapper.IMapper
import nz.co.test.transactions.data.services.Transaction
import nz.co.test.transactions.data.services.response.TransactionDTO
import java.time.OffsetDateTime

class Mapper : IMapper<List<TransactionDTO>, List<Transaction>> {

    override fun createTransactionList(modelDTO: List<TransactionDTO>): List<Transaction> {
        var mappedResult: List<Transaction> = emptyList()
        try {
            mappedResult = modelDTO.map {
                Transaction(
                    it.id,
                    OffsetDateTime.parse(it.transactionDate),
                    it.summary,
                    it.debit,
                    it.credit
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return mappedResult
    }
}