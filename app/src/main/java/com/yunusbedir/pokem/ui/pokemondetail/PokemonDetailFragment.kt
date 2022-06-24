package com.yunusbedir.pokem.ui.pokemondetail

import androidx.fragment.app.viewModels
import com.yunusbedir.pokem.databinding.FragmentPokemonDetailBinding
import com.yunusbedir.pokem.ui.base.BaseFragment
import com.yunusbedir.pokem.ui.pokemonlist.PokemonListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailFragment : BaseFragment<FragmentPokemonDetailBinding>(
    FragmentPokemonDetailBinding::inflate
) {

    private val pokemonListViewModel: PokemonListViewModel by viewModels()

    override fun setupUI() {

    }
}