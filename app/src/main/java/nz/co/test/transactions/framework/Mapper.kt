package nz.co.test.transactions.framework

import nz.co.test.transactions.data.mapper.IMapper
import nz.co.test.transactions.data.services.Transaction
import nz.co.test.transactions.data.services.response.TransactionDTO
import java.time.LocalDateTime
import java.time.OffsetDateTime
import javax.inject.Inject

class Mapper @Inject constructor(): IMapper<List<TransactionDTO>, List<Transaction>> {

    override fun createTransactionList(modelDTO: List<TransactionDTO>): List<Transaction> {
        var mappedResult: List<Transaction> = emptyList()
        var offset=  OffsetDateTime.now().offset
        try {
            mappedResult = modelDTO.map {
                Transaction(
                    it.id,
                    OffsetDateTime.of(
                        LocalDateTime.parse(it.transactionDate),
                        offset
                    ),
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