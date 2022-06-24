package com.yunusbedir.pokem.domain.usecase

import com.yunusbedir.pokem.domain.ResultData
import com.yunusbedir.pokem.domain.model.params.FetchPokemonListParams
import com.yunusbedir.pokem.domain.model.result.FetchPokemonListResult
import com.yunusbedir.pokem.domain.reposiyory.PokemonRepository
import com.yunusbedir.pokem.domain.toResultModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchPokemonListUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    fun invoke(params: FetchPokemonListParams) = flow<ResultData> {
        try {
            val data = pokemonRepository.fetchPokemonList(params.offset)
            val result = ResultData.Success<FetchPokemonListResult>(data.toResultModel())
            emit(result)
        } catch (e: Exception) {
            emit(ResultData.Fail(e.message.toString()))
        }
    }
}