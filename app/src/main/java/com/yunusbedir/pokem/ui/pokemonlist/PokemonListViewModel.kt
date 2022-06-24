package com.yunusbedir.pokem.ui.pokemonlist

import androidx.lifecycle.ViewModel
import com.yunusbedir.pokem.ui.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor() : ViewModel() {

    private val _pokemonListState = MutableStateFlow<UIState<String>?>(null)
    val pokemonListState = _pokemonListState.asStateFlow()

}