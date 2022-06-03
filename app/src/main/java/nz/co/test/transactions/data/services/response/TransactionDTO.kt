package nz.co.test.transactions.data.services.response

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class TransactionDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("transactionDate")
    val transactionDate: String,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("debit")
    val debit: BigDecimal,
    @SerializedName("credit")
    val credit: BigDecimal
)