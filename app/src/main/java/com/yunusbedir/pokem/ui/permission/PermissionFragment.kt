package com.yunusbedir.pokem.ui.permission

import androidx.fragment.app.viewModels
import com.yunusbedir.pokem.databinding.FragmentPermissionBinding
import com.yunusbedir.pokem.ui.base.BaseFragment
import com.yunusbedir.pokem.ui.pokemonlist.PokemonListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PermissionFragment : BaseFragment<FragmentPermissionBinding>(
    FragmentPermissionBinding::inflate
) {

    private val pokemonListViewModel: PokemonListViewModel by viewModels()

    override fun setupUI() {

    }
}