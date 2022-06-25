package com.yunusbedir.pokem.ui.pokemondetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yunusbedir.pokem.domain.ResultData
import com.yunusbedir.pokem.domain.model.result.PokemonDetailResult
import com.yunusbedir.pokem.domain.usecase.GetPokemonDetailUseCase
import com.yunusbedir.pokem.ui.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
) : ViewModel() {

    private val _pokemonDetailState = MutableStateFlow<UIState<PokemonDetailResult>?>(null)
    val pokemonDetailState = _pokemonDetailState.asStateFlow()

    fun getPokemonDetail(id: Int) {
        _pokemonDetailState.value = UIState.Loading()
        viewModelScope.launch {
            getPokemonDetailUseCase.invoke(id).collect {
                when (it) {
                    is ResultData.Fail<*> -> {
                        _pokemonDetailState.value = UIState.ErrorMessage(
                            it.message.toString()
                        )
                    }
                    is ResultData.Success<*> -> {
                        _pokemonDetailState.value = UIState.Success(
                            (it.data as PokemonDetailResult)
                        )
                    }
                }
            }
        }
    }
}