package com.yunusbedir.pokem.data.network.repository

import com.yunusbedir.pokem.data.network.service.PokemonService
import com.yunusbedir.pokem.domain.ErrorMapper
import com.yunusbedir.pokem.domain.ResultData
import com.yunusbedir.pokem.domain.model.result.FetchPokemonListResult
import com.yunusbedir.pokem.domain.model.result.PokemonDetailResult
import com.yunusbedir.pokem.domain.reposiyory.PokemonRepository
import com.yunusbedir.pokem.domain.toResultModel
import com.yunusbedir.pokem.domain.util.suspendCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonService: PokemonService,
    private val errorMapper: ErrorMapper
) : PokemonRepository {

    override suspend fun fetchPokemonList(offset: Int): ResultData<FetchPokemonListResult> =
        suspendCall(
            coroutineContext = Dispatchers.IO,
            errorMapper = errorMapper,
            call = { pokemonService.fetchPokemonList(offset) },
            map = { it.toResultModel() }
        )

    override suspend fun getPokemonDetail(id: Int): ResultData<PokemonDetailResult> =
        suspendCall(
            coroutineContext = Dispatchers.IO,
            errorMapper = errorMapper,
            call = { pokemonService.getPokemonDetail(id) },
            map = { it.toResultModel() }
        )
}