package com.yunusbedir.pokem.util

import androidx.recyclerview.widget.DiffUtil
import com.yunusbedir.pokem.domain.model.result.FetchPokemonListResult
import javax.inject.Inject

/**  **/
class GenericDiffUtil<T> @Inject constructor(): DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return when (oldItem) {
            is FetchPokemonListResult.ItemResult -> {
                oldItem as FetchPokemonListResult.ItemResult == newItem
            }
            else -> throw Exception("Not supported")
        }
    }
}