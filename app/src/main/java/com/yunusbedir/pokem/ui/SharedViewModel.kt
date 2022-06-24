package com.yunusbedir.pokem.ui

import androidx.lifecycle.ViewModel
import com.yunusbedir.pokem.util.Visibility
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {

    private val _loadingProgressState = MutableStateFlow<Int?>(null)
    val loadingProgressState = _loadingProgressState.asStateFlow()

    fun setProgressVisibility(@Visibility visibility: Int){
        _loadingProgressState.value = visibility
    }
}