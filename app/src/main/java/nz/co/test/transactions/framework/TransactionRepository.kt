package nz.co.test.transactions.framework

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import nz.co.test.transactions.R
import nz.co.test.transactions.data.mapper.IMapper
import nz.co.test.transactions.data.repository.ITransactionRepository
import nz.co.test.transactions.data.model.Transaction
import nz.co.test.transactions.data.services.TransactionsService
import nz.co.test.transactions.data.services.response.TransactionDTO
import nz.co.test.transactions.utils.ResponseResult
import nz.co.test.transactions.utils.apiServiceCall
import java.io.IOException
import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val transactionService: TransactionsService,
    private val mapper: IMapper<List<TransactionDTO>, List<Transaction>>,
    @ApplicationContext val appContext: Context
) : ITransactionRepository {
    override suspend fun getTransactionsList() = apiServiceCall(
        apiCall = { getTransactionsData() },
        errorMessage = appContext.getString(R.string.no_network_connection)
    )

    private suspend fun getTransactionsData(
    ): ResponseResult<List<Transaction>> {
        val response = transactionService.retrieveTransactions()
        if (response.isSuccessful)
            return ResponseResult.Success(
                mapper.createTransactionList(
                    response.body() ?: emptyList()
                )
            )
        return ResponseResult.Error(IOException(appContext.getString(R.string.no_network_connection)))
    }
}