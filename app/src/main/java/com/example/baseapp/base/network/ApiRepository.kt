package com.example.baseapp.base.network

import android.content.Context
import com.example.baseapp.config.RunConfig
import com.example.baseapp.constants.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.CertificateException
import java.util.concurrent.TimeUnit
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


class ApiRepository() {
    private var apiService: ApiService
    private var context: Context? = null
    init {
        val builder = provideOkHttpClient(context)
        apiService =
            Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).client(builder)
                .baseUrl(Constants.base_url).build()
                .create(ApiService::class.java)
    }


    fun getApiService(): ApiService {
        return apiService
    }

    private fun provideOkHttpClient(context: Context?): OkHttpClient{
        this.context=context
        val okHttpBuilder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
        okHttpBuilder.addNetworkInterceptor(loggingInterceptor)
        okHttpBuilder.callTimeout(1, TimeUnit.MINUTES)
        okHttpBuilder.writeTimeout(1, TimeUnit.SECONDS)
        okHttpBuilder.connectTimeout(50, TimeUnit.SECONDS)
        okHttpBuilder.readTimeout(50, TimeUnit.SECONDS)
        okHttpBuilder.retryOnConnectionFailure(false)
        okHttpBuilder.cache(null)
        return okHttpBuilder
            .build()
    }
}