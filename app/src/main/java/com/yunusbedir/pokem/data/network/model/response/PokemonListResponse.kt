package com.yunusbedir.pokem.data.network.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonListResponse(
    @Json(name = "count") val count: Int?,
    @Json(name = "next") val next: String?,
    @Json(name = "previous") val previous: String?,
    @Json(name = "results") val results: List<ItemResult>?,
)

@JsonClass(generateAdapter = true)
data class ItemResult(
    @Json(name = "name") val name: String?,
    @Json(name = "url") val url: String?
)
/*
{
    "count": 1154,
    "next": "https://pokeapi.co/api/v2/pokemon?offset=40&limit=20",
    "previous": "https://pokeapi.co/api/v2/pokemon?offset=0&limit=20",
    "results": [
        {
            "name": "spearow",
            "url": "https://pokeapi.co/api/v2/pokemon/21/"
        }
    ]
}
 */