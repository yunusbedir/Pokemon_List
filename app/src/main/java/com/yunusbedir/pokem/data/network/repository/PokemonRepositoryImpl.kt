package com.yunusbedir.pokem.data.network.repository

import com.yunusbedir.pokem.data.network.service.PokemonService
import com.yunusbedir.pokem.domain.reposiyory.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonService: PokemonService
) : PokemonRepository {

    override suspend fun fetchPokemonList(offset: Int) = withContext(Dispatchers.IO) {
        pokemonService.fetchPokemonList(offset)
    }

    override suspend fun getPokemonDetail(id: Int) = withContext(Dispatchers.IO) {
        pokemonService.getPokemonDetail(id)
    }
}