package dev.localiza.rentcar.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

internal class BasicLoggerInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        Log.d("BasicLoggerInterceptor", "Url Chamada: ${request.url.toString()}")

        val response = chain.proceed(request)

        return response
    }
}