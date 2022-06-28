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

    override fun setupUI() {
        sharedViewModel.setToolbarVisibility(View.GONE)
        binding.overlayButton.setOnClickListener {
            //Draw over other apps
            requestPermissionOverlay()
        }
    }

}