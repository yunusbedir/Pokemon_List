package com.yunusbedir.pokem.domain.reposiyory

import com.yunusbedir.pokem.data.network.model.response.PokemonDetailResponse
import com.yunusbedir.pokem.data.network.model.response.PokemonListResponse

interface PokemonRepository {
    suspend fun fetchPokemonList(offset: Int): PokemonListResponse
    suspend fun getPokemonDetail(id: Int): PokemonDetailResponse
}