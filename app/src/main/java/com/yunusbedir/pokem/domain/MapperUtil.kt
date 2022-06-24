package com.yunusbedir.pokem.domain

import com.yunusbedir.pokem.data.network.model.response.PokemonListResponse
import com.yunusbedir.pokem.domain.model.result.FetchPokemonListResult

fun PokemonListResponse.toResultModel(): FetchPokemonListResult {
    return FetchPokemonListResult(
        count = count,
        next = next,
        previous = previous,
        results = results?.map {
            FetchPokemonListResult.ItemResult(
                name = it.name,
                url = it.url
            )
        }
    )
}