package org.unizd.rma.saric.model

import com.google.gson.annotations.SerializedName

data class CreatureResponse(
    @SerializedName("data")
    val data: List<Creature>,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("total")
    val total: Int?
)

data class Creature(
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("image")
    val image: String?
)
