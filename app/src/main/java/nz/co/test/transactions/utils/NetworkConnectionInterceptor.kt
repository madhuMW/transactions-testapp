package nz.co.test.transactions.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import dagger.hilt.android.qualifiers.ApplicationContext
import nz.co.test.transactions.R
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkConnectionInterceptor(
    @ApplicationContext val appContext: Context
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!checkNetworkConnection())
            throw IOException(appContext.getString(R.string.no_network_connection))
        return chain.proceed(chain.request())
    }

    private fun checkNetworkConnection(): Boolean {
        val result: Boolean
        val connectivityManager =
            appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = connectivityManager.activeNetwork ?: return false
        val net =
            connectivityManager.getNetworkCapabilities(capabilities) ?: return false
        result = when {
            net.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            net.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            net.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
        return result
    }
}