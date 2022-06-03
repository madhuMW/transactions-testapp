package nz.co.test.transactions.data.repository

import nz.co.test.transactions.data.services.Transaction
import nz.co.test.transactions.utils.ResponseResult

interface ITransactionRepository {
    suspend fun getTransactionsList(): ResponseResult<List<Transaction>>
}