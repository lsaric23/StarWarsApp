package org.unizd.rma.saric.network

import org.unizd.rma.saric.model.Creature
import retrofit2.Response
import retrofit2.http.GET

interface StarWarsApi {

    @GET("api/v1/creatures")
    suspend fun getCreatures(): Response<List<Creature>>

    companion object {
        const val BASE_URL = "https://starwars-databank.vercel.app/"
    }
}