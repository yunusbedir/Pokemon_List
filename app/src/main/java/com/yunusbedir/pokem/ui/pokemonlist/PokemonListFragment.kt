package com.yunusbedir.pokem.ui.pokemonlist

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.yunusbedir.pokem.databinding.FragmentPokemonListBinding
import com.yunusbedir.pokem.domain.model.result.FetchPokemonListResult
import com.yunusbedir.pokem.ui.adapter.load.PagingLoadStateAdapter
import com.yunusbedir.pokem.ui.adapter.paging.PokemonListAdapter
import com.yunusbedir.pokem.ui.base.BaseFragment
import com.yunusbedir.pokem.util.RecyclerViewAdapterListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class PokemonListFragment : BaseFragment<FragmentPokemonListBinding>(
    FragmentPokemonListBinding::inflate
), RecyclerViewAdapterListener<FetchPokemonListResult.ItemResult> {

    private val pokemonListViewModel: PokemonListViewModel by viewModels()

    @Inject
    lateinit var pagingAdapter: PokemonListAdapter

    override fun setupUI() {
        initListeners()
        setupAdapter()
        initObservers()
    }

    private fun initListeners() {
        binding.loadingStateInclude.retryButton.setOnClickListener { pagingAdapter.refresh() }
        pagingAdapter.setRecyclerViewAdapterListener(this)
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

    override fun itemClickListener(item: FetchPokemonListResult.ItemResult) {
        if (item.id != null) {
            val action =
                PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailFragment(item.id)
            findNavController().navigate(action)
        }else{
            // show toast "can not found item id"
        }
    }
}