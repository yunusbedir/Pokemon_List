package com.yunusbedir.pokem.data.network.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonDetailResponse(
    @Json(name = "height") var height: Int? = null,
    @Json(name = "id") var id: Int? = null,
    @Json(name = "name") var name: String? = null,
    @Json(name = "sprites") var sprites: Sprites? = Sprites(),
    @Json(name = "weight") var weight: Int? = null
)

@JsonClass(generateAdapter = true)
data class Sprites(
    @Json(name = "back_shiny") var backShiny: String? = null,
    @Json(name = "front_shiny") var frontShiny: String? = null
)
/*
 */