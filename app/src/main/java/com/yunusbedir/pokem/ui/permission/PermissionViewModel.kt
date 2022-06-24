package com.yunusbedir.pokem.ui.permission

import androidx.lifecycle.ViewModel
import com.yunusbedir.pokem.ui.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PermissionViewModel @Inject constructor() : ViewModel() {

    private val _permissionState = MutableStateFlow<UIState<String>?>(null)
    val permissionState = _permissionState.asStateFlow()
}