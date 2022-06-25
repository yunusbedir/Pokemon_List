package com.yunusbedir.pokem.util

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadWithUrl(url: String) {
    Glide.with(this).load(url).into(this)

}