package com.yunusbedir.pokem.data.repository

import com.yunusbedir.pokem.domain.ErrorMapper
import com.yunusbedir.pokem.domain.ResultData
import com.yunusbedir.pokem.domain.model.result.FetchPokemonListResult
import com.yunusbedir.pokem.domain.model.result.PokemonDetailResult
import com.yunusbedir.pokem.domain.reposiyory.PokemonRepository

class FakePokemonRepository : PokemonRepository {

    private val pokemonList = mutableListOf<FetchPokemonListResult>()
    private val pokemonDetailList = mutableListOf<PokemonDetailResult>()

    fun insertPokemonDetail(list: List<PokemonDetailResult>) {
        pokemonDetailList.addAll(list)
    }

    override suspend fun fetchPokemonList(offset: Int): ResultData<FetchPokemonListResult> {
        return try {
            ResultData.Success(pokemonList[offset])
        } catch (e: Exception) {
            ResultData.Fail(ErrorMapper().mapError(e))
        }
    }

    override suspend fun getPokemonDetail(id: Int): ResultData<PokemonDetailResult> {
        return try {
            ResultData.Success(pokemonDetailList[id])
        } catch (e: Exception) {
            ResultData.Fail(ErrorMapper().mapError(e))
        }
    }
}