package com.yunusbedir.pokem.domain

import com.yunusbedir.pokem.data.network.model.response.PokemonDetailResponse
import com.yunusbedir.pokem.data.network.model.response.PokemonListResponse
import com.yunusbedir.pokem.domain.model.result.FetchPokemonListResult
import com.yunusbedir.pokem.domain.model.result.PokemonDetailResult

fun PokemonListResponse.toResultModel(): FetchPokemonListResult {
    return FetchPokemonListResult(
        count = count,
        next = next?.split("&")?.get(0)?.split("=")?.get(1),
        previous = previous?.split("&")?.get(0)?.split("=")?.get(1),
        results = results?.map {
            FetchPokemonListResult.ItemResult(
                name = it.name,
                url = it.url,
                id = it.url?.split("pokemon")?.get(1)?.split("/")?.get(1)?.toInt()
            )
        }
    )
}

fun PokemonDetailResponse.toResultModel(): PokemonDetailResult {
    return PokemonDetailResult(
        pokemonName = name,
        height = height.toString(),
        weight = weight.toString(),
        iconFront = sprites?.frontShiny,
        iconBack = sprites?.backShiny
    )
}