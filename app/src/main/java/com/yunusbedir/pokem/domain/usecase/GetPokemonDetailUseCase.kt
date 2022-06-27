package com.yunusbedir.pokem.domain.usecase

import com.yunusbedir.pokem.domain.Error
import com.yunusbedir.pokem.domain.ResultData
import com.yunusbedir.pokem.domain.model.result.PokemonDetailResult
import com.yunusbedir.pokem.domain.reposiyory.PokemonRepository
import com.yunusbedir.pokem.ui.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {

    fun invoke(id: Int): Flow<UIState<PokemonDetailResult>> = flow {
        try {
            when (val result = pokemonRepository.getPokemonDetail(id)) {
                is ResultData.Success -> {
                    emit(UIState.Success(result.data))
                }
                is ResultData.Fail -> {
                    emit(UIState.ErrorMessage(errorMessage = result.message.errorMessage))
                }
            }
        } catch (e: Exception) {
            emit(UIState.ErrorMessage(Error.defaultMessage))
        }
    }
}