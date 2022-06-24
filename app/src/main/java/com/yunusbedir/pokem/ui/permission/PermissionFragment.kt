package com.yunusbedir.pokem.ui.permission

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.yunusbedir.pokem.databinding.FragmentPermissionBinding
import com.yunusbedir.pokem.ui.UIState
import com.yunusbedir.pokem.ui.base.BaseFragment
import com.yunusbedir.pokem.util.requestPermissionOverlay
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PermissionFragment : BaseFragment<FragmentPermissionBinding>(
    FragmentPermissionBinding::inflate
) {

    private val permissionViewModel: PermissionViewModel by viewModels()

    override fun setupUI() {
        initObservers()
        binding.overlayButton.setOnClickListener {
            //Draw over other apps
            requestPermissionOverlay()
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            permissionViewModel.permissionState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    when (it) {
                        is UIState.ErrorMessage -> {
                            sharedViewModel.setProgressVisibility(View.GONE)
                        }
                        is UIState.Loading -> {
                            sharedViewModel.setProgressVisibility(View.VISIBLE)

                        }
                        is UIState.Success -> {
                            sharedViewModel.setProgressVisibility(View.GONE)
                        }
                        null -> {
                            sharedViewModel.setProgressVisibility(View.GONE)
                        }
                    }
                }
        }
    }
}