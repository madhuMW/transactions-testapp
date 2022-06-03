package nz.co.test.transactions.data.services

import nz.co.test.transactions.data.services.response.TransactionDTO
import retrofit2.Response
import retrofit2.http.GET

interface TransactionsService {

    companion object {
        const val BASE_URL =
            "https://gist.githubusercontent.com/Josh-Ng/500f2716604dc1e8e2a3c6d31ad01830/raw/4d73acaa7caa1167676445c922835554c5572e82/"
    }

    @GET("test-data.json")
    suspend fun retrieveTransactions(): Response<List<TransactionDTO>>
}

