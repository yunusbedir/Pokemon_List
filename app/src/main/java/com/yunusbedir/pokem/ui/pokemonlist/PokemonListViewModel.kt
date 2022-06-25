package com.yunusbedir.pokem.ui.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yunusbedir.pokem.domain.model.result.FetchPokemonListResult
import com.yunusbedir.pokem.domain.usecase.FetchPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val fetchPokemonListUseCase: FetchPokemonListUseCase
) : ViewModel() {

    private lateinit var _pokemonListState: Flow<PagingData<FetchPokemonListResult.ItemResult>>
    val pokemonListState: Flow<PagingData<FetchPokemonListResult.ItemResult>>
        get() = _pokemonListState

    init {
        fetchPokemonList()
    }

    private fun fetchPokemonList() = viewModelScope.launch {
        _pokemonListState = fetchPokemonListUseCase.invoke()
            .cachedIn(viewModelScope)
    }

}