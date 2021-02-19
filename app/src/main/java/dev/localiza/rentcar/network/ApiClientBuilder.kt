package dev.localiza.rentcar.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.ConnectionSpec
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okio.Timeout
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClientBuilder {

    private val baseClient = OkHttpClient()
    private val gsonDefault = GsonBuilder().setDateFormat("yyyy-MM-dd").create()
    private val defaultUrl = "https://g6rentcar.azurewebsites.net/"

    fun <T> createServiceApi(responseType: Class<T>,
                             baseUrl: String = defaultUrl,
                             gsonConfig: Gson = gsonDefault,
                             vararg interceptor: Interceptor): T {
        val clientBuilder = baseClient.newBuilder()

        clientBuilder.addInterceptor(BasicLoggerInterceptor())

        interceptor.forEach {
            clientBuilder.addInterceptor(it)
        }

        val retrofit = Retrofit.Builder()
                .client(clientBuilder.build())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gsonConfig))
                .build()

        return retrofit.create(responseType)
    }

}
