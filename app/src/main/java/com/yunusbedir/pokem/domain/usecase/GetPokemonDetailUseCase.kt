package com.yunusbedir.pokem.domain.usecase

import com.yunusbedir.pokem.domain.ResultData
import com.yunusbedir.pokem.domain.reposiyory.PokemonRepository
import com.yunusbedir.pokem.domain.toResultModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {

    fun invoke(id: Int): Flow<ResultData> = flow {
        try {
            val response = pokemonRepository.getPokemonDetail(id)
            val result = response.toResultModel()
            emit(ResultData.Success(result))
        } catch (e: Exception) {
            emit(ResultData.Fail(e.message))
        }
    }
}