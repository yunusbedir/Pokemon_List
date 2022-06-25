package com.yunusbedir.pokem.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.yunusbedir.pokem.util.GenericDiffUtil
import com.yunusbedir.pokem.util.RecyclerViewAdapterListener

abstract class BasePagingAdapter<T : Any, VB : ViewBinding, VH : BasePagingAdapter.BaseViewHolder<T, VB>>(
    private val genericDiffUtil: GenericDiffUtil<T>
) : PagingDataAdapter<T, VH>(genericDiffUtil) {

    private var adapterListener: RecyclerViewAdapterListener<T>? =
        null

    fun setRecyclerViewAdapterListener(adapterListener: RecyclerViewAdapterListener<T>) {
        this.adapterListener = adapterListener
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position)) {
            adapterListener?.itemClickListener(it)
        }
    }

    fun getBinding(
        parent: ViewGroup,
        bindingInflater: (inflater: LayoutInflater, parent: ViewGroup, attachToParent: Boolean) -> VB
    ): VB {
        return bindingInflater(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }

    abstract class BaseViewHolder<T, VB : ViewBinding>(private val binding: VB) :
        RecyclerView.ViewHolder(binding.root) {

        abstract fun bind(
            item: T?,
            itemClickCallback: (T) -> Unit
        )
    }

}