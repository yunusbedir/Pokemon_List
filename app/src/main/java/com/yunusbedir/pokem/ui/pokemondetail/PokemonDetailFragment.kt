package com.yunusbedir.pokem.ui.pokemondetail

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.yunusbedir.pokem.databinding.FragmentPokemonDetailBinding
import com.yunusbedir.pokem.ui.UIState
import com.yunusbedir.pokem.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonDetailFragment : BaseFragment<FragmentPokemonDetailBinding>(
    FragmentPokemonDetailBinding::inflate
) {

    private val pokemonDetailViewModel: PokemonDetailViewModel by viewModels()

    override fun setupUI() {
        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launch {
            pokemonDetailViewModel.pokemonDetailState
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