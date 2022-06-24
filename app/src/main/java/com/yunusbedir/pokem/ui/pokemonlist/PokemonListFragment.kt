package com.yunusbedir.pokem.ui.pokemonlist

import androidx.fragment.app.viewModels
import com.yunusbedir.pokem.databinding.FragmentPokemonListBinding
import com.yunusbedir.pokem.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonListFragment : BaseFragment<FragmentPokemonListBinding>(
    FragmentPokemonListBinding::inflate
) {

    private val pokemonListViewModel: PokemonListViewModel by viewModels()

    override fun setupUI() {

    }
}