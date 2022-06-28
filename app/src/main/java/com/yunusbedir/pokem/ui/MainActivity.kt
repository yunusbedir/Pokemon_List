package com.yunusbedir.pokem.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.get
import androidx.navigation.ui.*
import com.yunusbedir.pokem.R
import com.yunusbedir.pokem.databinding.ActivityMainBinding
import com.yunusbedir.pokem.ui.permission.PermissionFragment
import com.yunusbedir.pokem.ui.permission.PermissionFragmentDirections
import com.yunusbedir.pokem.ui.pokemonlist.PokemonListFragmentDirections
import com.yunusbedir.pokem.util.checkPermissionOverlay
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val sharedViewModel: SharedViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration


    private var _binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setNavigationGraph()
        setContentView(_binding?.root)
        initObservers()
        initListeners()
    }

    private fun initListeners() {
        _binding?.navView?.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_close_application -> {
                    finishAndRemoveTask()
                }
            }

            NavigationUI.onNavDestinationSelected(it, navController)
            _binding?.drawerLayout?.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun setNavigationGraph() {
        setSupportActionBar(_binding!!.toolbar)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        _binding?.navView?.setupWithNavController(navController)
        appBarConfiguration = AppBarConfiguration(navController.graph, _binding?.drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)

        if (checkPermissionOverlay().not()) {
            val action = PermissionFragmentDirections.actionGlobalPermissionFragment()
            navController.navigate(action)
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            sharedViewModel.toolbarVisibilityState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    if (it != null) {
                        _binding?.toolbar?.visibility = it
                    }
                }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (checkPermissionOverlay())
            _binding?.navHostFragmentContainer?.let {
                findNavController(it.id).navigateUp()
            }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding?.root
    }
}