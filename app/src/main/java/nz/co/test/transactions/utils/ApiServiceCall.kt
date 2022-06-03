package nz.co.test.transactions.utils

import java.io.IOException

suspend fun <T : Any> apiServiceCall(
    apiCall: suspend () -> ResponseResult<T>,
    errorMessage: String
): ResponseResult<T> =
    try {
        apiCall.invoke()
    } catch (e: Exception) {
        e.printStackTrace()
        ResponseResult.Error(IOException(errorMessage, e))
    }