package com.yunusbedir.pokem.ui.adapter.load

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yunusbedir.pokem.databinding.ItemViewLoadingStateBinding

class PagingLoadStateAdapter<T : Any, VH : RecyclerView.ViewHolder>(
    private val adapter: PagingDataAdapter<T, VH>
) : LoadStateAdapter<PagingLoadStateAdapter.NetworkStateItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        NetworkStateItemViewHolder.from(parent)

    override fun onBindViewHolder(holder: NetworkStateItemViewHolder, loadState: LoadState) =
        holder.bind(loadState) { adapter.retry() }

    class NetworkStateItemViewHolder(
        private val binding: ItemViewLoadingStateBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState, retryCallback: () -> Unit) {
            with(binding) {
                retryButton.setOnClickListener { retryCallback() }
                progressBar.isVisible = loadState is LoadState.Loading
                errorGroup.isVisible = loadState is LoadState.Error
                errorMsg.isVisible =
                    !(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()
                errorMsg.text = (loadState as? LoadState.Error)?.error?.message
            }
        }


        companion object {
            fun from(parent: ViewGroup): NetworkStateItemViewHolder {
                val binding = ItemViewLoadingStateBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return NetworkStateItemViewHolder(
                    binding
                )
            }
        }
    }
}
