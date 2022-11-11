package com.example.cryptoapitextview.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.coingecko.com/api/v3/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface CryptoApiService {
    @GET("coins/markets?vs_currency=inr&order=market_cap_desc&per_page=100&page=1&sparkline=false&price_change_percentage=24h")
    suspend fun getCryptoDetailsAsync():
            String
}

object CryptoDetailsApi {
    val retrofitService: CryptoApiService by lazy {
        retrofit.create(CryptoApiService::class.java)
    }
}