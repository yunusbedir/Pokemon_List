package com.yunusbedir.pokem.ui.adapter.paging

import android.view.ViewGroup
import com.yunusbedir.pokem.databinding.ItemViewPokemonListBinding
import com.yunusbedir.pokem.domain.model.result.FetchPokemonListResult.ItemResult
import com.yunusbedir.pokem.ui.adapter.BasePagingAdapter
import com.yunusbedir.pokem.util.GenericDiffUtil
import javax.inject.Inject

class PokemonListAdapter @Inject constructor(
    private val genericDiffUtil: GenericDiffUtil<ItemResult>
) : BasePagingAdapter<ItemResult, ItemViewPokemonListBinding, PokemonListAdapter.PokemonListViewHolder>(
    genericDiffUtil
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        val binding = getBinding(parent, ItemViewPokemonListBinding::inflate)
        return PokemonListViewHolder(binding)
    }

    class PokemonListViewHolder(private val binding: ItemViewPokemonListBinding) :
        BasePagingAdapter.BaseViewHolder<ItemResult, ItemViewPokemonListBinding>(binding) {

        override fun bind(
            item: ItemResult?,
            itemClickCallback: (ItemResult) -> Unit
        ) {
            binding.root.text = item?.name.toString()
            binding.root.setOnClickListener {
                item?.let { it1 -> itemClickCallback.invoke(it1) }
            }
        }

    }
}