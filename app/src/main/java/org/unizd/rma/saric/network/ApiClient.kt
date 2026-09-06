package org.unizd.rma.saric.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(StarWarsApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val starWarsApi: StarWarsApi by lazy {
        retrofit.create(StarWarsApi::class.java)
    }
}