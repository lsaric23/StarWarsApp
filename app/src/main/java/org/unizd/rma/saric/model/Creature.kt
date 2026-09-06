package org.unizd.rma.saric.model

import java.io.Serializable

data class Creature(
    val _id: String?,
    val name: String,
    val description: String?,
    val image: String?,
    val homeworld: String?,
    val species: String?,
    val height: String?
) : Serializable
