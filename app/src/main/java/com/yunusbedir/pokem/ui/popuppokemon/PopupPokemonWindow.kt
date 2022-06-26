package com.yunusbedir.pokem.ui.popuppokemon

import android.content.Context
import android.content.Context.WINDOW_SERVICE
import android.graphics.PixelFormat
import android.os.Build
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import com.yunusbedir.pokem.databinding.PopupPokemonBinding
import com.yunusbedir.pokem.domain.model.result.PokemonDetailResult
import com.yunusbedir.pokem.util.WindowListener
import com.yunusbedir.pokem.util.getBinding
import com.yunusbedir.pokem.util.loadWithUrl


class PopupPokemonWindow(
    private val context: Context,
    private val windowListener: WindowListener
) {

    private var mView: PopupPokemonBinding? = null
    private var mParams: WindowManager.LayoutParams? = null
    private var mWindowManager: WindowManager? = null
    private var layoutInflater: LayoutInflater? = null

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mParams = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
            )
        }
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mView = layoutInflater?.getBinding(PopupPokemonBinding::inflate)
        mView?.closeWindowButton?.setOnClickListener {
            close()
        }
        mParams?.gravity = Gravity.CENTER
        mWindowManager = context.getSystemService(WINDOW_SERVICE) as WindowManager
    }

    fun open() {
        try {
            if (mView?.root?.windowToken == null) {
                if (mView?.root?.parent == null) {
                    mWindowManager!!.addView(mView?.root, mParams)
                }
            }
        } catch (e: Exception) {
            Log.d("PopupPokemonWindow", e.toString())
        }
    }

    private fun close() {
        try {
            (context.getSystemService(WINDOW_SERVICE) as WindowManager).removeView(mView?.root)
            mView?.root?.invalidate()
            (mView?.root as ViewGroup).removeAllViews()
            windowListener.closed()
        } catch (e: Exception) {
            Log.d("PopupPokemonWindow", e.toString())
        }
    }

    fun update(pokemonDetailResult: PokemonDetailResult?) {
        mView?.let {
            it.pokemonNameTextView.text = pokemonDetailResult?.pokemonName.toString()
            it.frontIconImageView.loadWithUrl(pokemonDetailResult?.iconFront.toString())
            it.backIconImageView.loadWithUrl(pokemonDetailResult?.iconBack.toString())
        }
    }
}