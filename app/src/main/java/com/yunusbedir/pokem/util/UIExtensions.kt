package com.yunusbedir.pokem.util

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadWithUrl(url: String) {
    Glide.with(this).load(url).into(this)
}

fun <VB> ViewGroup.getBinding(
    bindingInflater: (inflater: LayoutInflater, parent: ViewGroup, attachToParent: Boolean) -> VB
): VB {
    return bindingInflater(
        LayoutInflater.from(context),
        this,
        false
    )
}

fun <VB> LayoutInflater.getBinding(
    bindingInflater: (inflater: LayoutInflater) -> VB
): VB {
    return bindingInflater.invoke(this)
}