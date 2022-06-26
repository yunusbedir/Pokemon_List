package com.yunusbedir.pokem.ui.pokemondetail

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.google.firebase.analytics.FirebaseAnalytics
import com.yunusbedir.pokem.databinding.FragmentPokemonDetailBinding
import com.yunusbedir.pokem.domain.model.result.PokemonDetailResult
import com.yunusbedir.pokem.firebase.FirebaseEvent
import com.yunusbedir.pokem.ui.UIState
import com.yunusbedir.pokem.ui.base.BaseFragment
import com.yunusbedir.pokem.util.loadWithUrl
import com.yunusbedir.pokem.util.startService
import com.yunusbedir.pokem.util.trackEventFirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PokemonDetailFragment : BaseFragment<FragmentPokemonDetailBinding>(
    FragmentPokemonDetailBinding::inflate
) {
    private val args: PokemonDetailFragmentArgs by navArgs()
    private val pokemonDetailViewModel: PokemonDetailViewModel by viewModels()

    @Inject
    lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun setupUI() {
        initObservers()
        initListeners()
        pokemonDetailViewModel.getPokemonDetail(args.pokemonId)
    }

    private fun initListeners() {
        binding.showInOverlayButton.setOnClickListener {
            startService()
            FirebaseEvent.SHOW_POKEMON_IN_OVERLAY.trackEventFirebaseAnalytics(firebaseAnalytics)
        }
        binding.loadingStateInclude.retryButton.setOnClickListener {
            pokemonDetailViewModel.getPokemonDetail(args.pokemonId)
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            pokemonDetailViewModel.pokemonDetailState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    when (it) {
                        is UIState.ErrorMessage -> {
                            with(binding.loadingStateInclude) {
                                container.visibility = View.VISIBLE
                                errorGroup.visibility = View.VISIBLE
                                progressBar.visibility = View.GONE
                                errorMsg.text = it.errorMessage
                            }
                            binding.container.visibility = View.GONE
                        }
                        is UIState.Loading -> {
                            with(binding.loadingStateInclude) {
                                container.visibility = View.VISIBLE
                                errorGroup.visibility = View.GONE
                                progressBar.visibility = View.VISIBLE
                            }
                            binding.container.visibility = View.GONE
                        }
                        is UIState.Success -> {
                            with(binding.loadingStateInclude) {
                                container.visibility = View.GONE
                            }
                            binding.container.visibility = View.VISIBLE
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
                            with(binding.loadingStateInclude) {
                                container.visibility = View.GONE
                            }
                            binding.container.visibility = View.GONE
                        }
                    }
                }
        }
    }

    private fun startService() {
        if (pokemonDetailViewModel.pokemonDetailState.value is UIState.Success) {
            val pokemonDetailResult =
                pokemonDetailViewModel.pokemonDetailState.value as UIState.Success<PokemonDetailResult>
            requireContext().startService(pokemonDetailResult.data)
        }
    }
}