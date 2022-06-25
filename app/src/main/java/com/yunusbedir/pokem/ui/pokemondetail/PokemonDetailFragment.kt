package com.yunusbedir.pokem.ui.pokemondetail

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.yunusbedir.pokem.databinding.FragmentPokemonDetailBinding
import com.yunusbedir.pokem.ui.UIState
import com.yunusbedir.pokem.ui.base.BaseFragment
import com.yunusbedir.pokem.util.loadWithUrl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonDetailFragment : BaseFragment<FragmentPokemonDetailBinding>(
    FragmentPokemonDetailBinding::inflate
) {
    private val args: PokemonDetailFragmentArgs by navArgs()
    private val pokemonDetailViewModel: PokemonDetailViewModel by viewModels()

    override fun setupUI() {
        initObservers()
        pokemonDetailViewModel.getPokemonDetail(args.pokemonId)
    }

    private fun initObservers() {
        lifecycleScope.launch {
            pokemonDetailViewModel.pokemonDetailState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    when (it) {
                        is UIState.ErrorMessage -> {
                            sharedViewModel.setProgressVisibility(View.GONE)
                            binding.pokemonHeightLabel.text = it.errorMessage
                        }
                        is UIState.Loading -> {
                            sharedViewModel.setProgressVisibility(View.VISIBLE)
                        }
                        is UIState.Success -> {
                            sharedViewModel.setProgressVisibility(View.GONE)
                            binding.pokemonNameTextView.text = it.data.pokemonName
                            it.data.iconFront?.let { url ->
                                binding.frontIconImageView.loadWithUrl(url)
                            }
                            binding.pokemonHeightTextView.text = it.data.height
                            binding.pokemonWeightTextView.text = it.data.weight
                            binding.showInOverlayButton.text =
                                "Show ${it.data.pokemonName} In Overlay"
                        }
                        null -> {
                            sharedViewModel.setProgressVisibility(View.GONE)
                        }
                    }
                }
        }
    }
}