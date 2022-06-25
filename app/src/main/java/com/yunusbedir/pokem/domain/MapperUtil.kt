package com.yunusbedir.pokem.domain

import com.yunusbedir.pokem.data.network.model.response.PokemonListResponse
import com.yunusbedir.pokem.domain.model.result.FetchPokemonListResult

fun PokemonListResponse.toResultModel(): FetchPokemonListResult {
    return FetchPokemonListResult(
        count = count,
        next = next?.split("&")?.get(0)?.split("=")?.get(1),
        previous = previous?.split("&")?.get(0)?.split("=")?.get(1),
        results = results?.map {
            FetchPokemonListResult.ItemResult(
                name = it.name,
                url = it.url
            )
        }
    )
}