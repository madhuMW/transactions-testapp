package nz.co.test.transactions.data.repository

import nz.co.test.transactions.data.model.Transaction
import nz.co.test.transactions.utils.ResponseResult

interface ITransactionRepository {
    suspend fun getTransactionsList(): ResponseResult<List<Transaction>>
}