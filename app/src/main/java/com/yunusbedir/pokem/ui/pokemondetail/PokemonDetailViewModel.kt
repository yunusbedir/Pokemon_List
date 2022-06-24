package com.yunusbedir.pokem.ui.pokemondetail

import androidx.lifecycle.ViewModel
import com.yunusbedir.pokem.ui.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor() : ViewModel() {

    private val _pokemonDetailState = MutableStateFlow<UIState<String>?>(null)
    val pokemonDetailState = _pokemonDetailState.asStateFlow()
}