package com.yunusbedir.pokem.data.network.service

import com.yunusbedir.pokem.data.network.FakeData.pokemonDetailResponseList
import com.yunusbedir.pokem.data.network.FakeData.pokemonListResponseList
import com.yunusbedir.pokem.data.network.model.response.PokemonDetailResponse
import com.yunusbedir.pokem.data.network.model.response.PokemonListResponse

class FakePokemonService : PokemonService {

    override suspend fun fetchPokemonList(offset: Int): PokemonListResponse {
        return when (offset) {
            0 -> {
                pokemonListResponseList[0]
            }
            20 -> {
                pokemonListResponseList[1]
            }
            else -> {
                throw Exception()
            }
        }
    }

    override suspend fun getPokemonDetail(id: Int): PokemonDetailResponse {
        return try {
            pokemonDetailResponseList.first {
                it.id == id
            }
        } catch (e: Exception) {
            throw e
        }
    }


}