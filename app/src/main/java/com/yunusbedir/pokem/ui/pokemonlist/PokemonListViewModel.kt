package com.yunusbedir.pokem.ui.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yunusbedir.pokem.domain.ResultData
import com.yunusbedir.pokem.domain.model.params.FetchPokemonListParams
import com.yunusbedir.pokem.domain.model.result.FetchPokemonListResult
import com.yunusbedir.pokem.domain.usecase.FetchPokemonListUseCase
import com.yunusbedir.pokem.ui.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val fetchPokemonListUseCase: FetchPokemonListUseCase
) : ViewModel() {

    private val _pokemonListState = MutableStateFlow<UIState<FetchPokemonListResult>?>(null)
    val pokemonListState = _pokemonListState.asStateFlow()

    fun fetchList() {
        _pokemonListState.value = UIState.Loading()
        viewModelScope.launch {
            fetchPokemonListUseCase.invoke(FetchPokemonListParams(20)).collect {
                when(it){
                    is ResultData.Fail<*> -> {
                        _pokemonListState.value = UIState.ErrorMessage(errorMessage = it.message.toString())
                    }
                    is ResultData.Success<*> -> {
                        _pokemonListState.value = UIState.Success(data = it.data as FetchPokemonListResult)
                    }
                }
            }
        }
    }

}