package org.unizd.rma.saric.network

import org.unizd.rma.saric.model.Creature
import org.unizd.rma.saric.model.CreatureResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StarWarsApi {

    @GET("creatures")
    suspend fun getCreatures(): Response<CreatureResponse>

    @GET("creatures/{id}")
    suspend fun getCreatureById(@Path("id") id: String): Response<Creature>

    companion object {
        const val BASE_URL = "https://starwars-databank-server.onrender.com/api/v1/"
    }
}