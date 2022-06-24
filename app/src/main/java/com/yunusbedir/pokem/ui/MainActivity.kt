package com.yunusbedir.pokem.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.get
import com.yunusbedir.pokem.R
import com.yunusbedir.pokem.databinding.ActivityMainBinding
import com.yunusbedir.pokem.ui.permission.PermissionFragmentDirections
import com.yunusbedir.pokem.util.checkPermissionOverlay
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val sharedViewModel: SharedViewModel by viewModels()

    private var _binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setNavigationGraph()
        setContentView(_binding?.root)
        initObservers()
    }

    private fun setNavigationGraph() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.main_graph)
        navGraph.setStartDestination(
            if (checkPermissionOverlay()) {
                navGraph[R.id.pokemonListFragment].id
            } else {
                navGraph[R.id.permissionFragment].id
            }
        )
        navController.graph = navGraph
    }

    private fun initObservers() {
        lifecycleScope.launch {
            sharedViewModel.loadingProgressState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    if (it != null) {
                        _binding?.loadingProgressContainer?.visibility = it
                    }
                }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (checkPermissionOverlay())
            _binding?.navHostFragmentContainer?.let {
                val action =
                    PermissionFragmentDirections.actionPermissionFragmentToPokemonListFragment()
                findNavController(it.id).navigate(action)
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding?.root
    }
}