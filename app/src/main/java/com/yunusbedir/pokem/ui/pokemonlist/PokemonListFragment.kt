package com.yunusbedir.pokem.ui.pokemonlist

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.yunusbedir.pokem.databinding.FragmentPokemonListBinding
import com.yunusbedir.pokem.ui.UIState
import com.yunusbedir.pokem.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonListFragment : BaseFragment<FragmentPokemonListBinding>(
    FragmentPokemonListBinding::inflate
) {

    private val pokemonListViewModel: PokemonListViewModel by viewModels()

    override fun setupUI() {
        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launch {
            pokemonListViewModel.pokemonListState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    when(it){
                        is UIState.ErrorMessage -> {
                            sharedViewModel.setProgressVisibility(View.GONE)
                        }
                        is UIState.Loading -> {
                            sharedViewModel.setProgressVisibility(View.VISIBLE)

                        }
                        is UIState.Success -> {
                            sharedViewModel.setProgressVisibility(View.GONE)
                        }
                        null -> {
                            sharedViewModel.setProgressVisibility(View.GONE)
                        }
                    }
                }
        }
    }
}