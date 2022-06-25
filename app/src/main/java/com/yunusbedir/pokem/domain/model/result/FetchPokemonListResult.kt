package com.yunusbedir.pokem.domain.model.result

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FetchPokemonListResult(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<ItemResult>?,
) {
    @JsonClass(generateAdapter = true)
    data class ItemResult(
        val name: String?,
        val url: String?,
        val id: Int?
    )
}