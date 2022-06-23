package com.yunusbedir.pokem.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yunusbedir.pokem.R
import com.yunusbedir.pokem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding?.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding?.root
    }
}