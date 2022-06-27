package com.yunusbedir.pokem.domain.reposiyory

import com.yunusbedir.pokem.data.network.model.response.PokemonListResponse
import com.yunusbedir.pokem.domain.ResultData
import com.yunusbedir.pokem.domain.model.result.FetchPokemonListResult
import com.yunusbedir.pokem.domain.model.result.PokemonDetailResult

interface PokemonRepository {
    suspend fun fetchPokemonList(offset: Int): ResultData<FetchPokemonListResult>
    suspend fun getPokemonDetail(id: Int): ResultData<PokemonDetailResult>
}