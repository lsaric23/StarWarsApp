package org.unizd.rma.saric.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val starWarsApi: StarWarsApi by lazy {
        Retrofit.Builder()
            .baseUrl(StarWarsApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StarWarsApi::class.java)
    }
}