package com.yunusbedir.pokem.ui.pokemonlist

import android.view.View
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.yunusbedir.pokem.databinding.FragmentPokemonListBinding
import com.yunusbedir.pokem.ui.adapter.load.PagingLoadStateAdapter
import com.yunusbedir.pokem.ui.adapter.paging.PokemonListAdapter
import com.yunusbedir.pokem.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class PokemonListFragment : BaseFragment<FragmentPokemonListBinding>(
    FragmentPokemonListBinding::inflate
) {

    private val pokemonListViewModel: PokemonListViewModel by viewModels()

    @Inject
    lateinit var pagingAdapter: PokemonListAdapter

    override fun setupUI() {
        setupAdapter()
        initObservers()
        initListeners()
    }

    private fun initListeners() {
        binding.loadingStateInclude.retryButton.setOnClickListener { pagingAdapter.refresh() }
    }

    private fun setupAdapter() {
        with(pagingAdapter) {
            binding.pokemonListRecyclerView.adapter = withLoadStateHeaderAndFooter(
                header = PagingLoadStateAdapter(this),
                footer = PagingLoadStateAdapter(this)
            )
        }
    }

    private fun initObservers() {
        launchOnLifecycleScope {
            pokemonListViewModel.pokemonListState.collectLatest {
                pagingAdapter.submitData(it)
            }
        }
        launchOnLifecycleScope {
            pagingAdapter.loadStateFlow.collectLatest {
                when (it.refresh) {
                    is LoadState.NotLoading -> {
                        binding.loadingStateInclude.container.visibility = View.GONE
                    }
                    LoadState.Loading -> {
                        binding.loadingStateInclude.errorGroup.visibility = View.GONE
                        binding.loadingStateInclude.progressBar.visibility = View.VISIBLE
                        binding.loadingStateInclude.container.visibility = View.VISIBLE
                    }
                    is LoadState.Error -> {
                        binding.loadingStateInclude.errorGroup.visibility = View.VISIBLE
                        binding.loadingStateInclude.progressBar.visibility = View.GONE
                        binding.loadingStateInclude.container.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}