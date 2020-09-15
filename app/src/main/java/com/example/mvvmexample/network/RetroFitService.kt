package com.example.mvvmexample.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class RetroFitService {
    companion object {

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
        fun getService(): Api {
            var retrofit = Retrofit.Builder()
                .baseUrl("http://ws-srv-net.in.webmyne.com/Applications/GAIL/GAIL_MeterReader_WCF/Services/MeterReading.svc/json/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

            return retrofit.create<Api>(Api::class.java)
        }
    }
}