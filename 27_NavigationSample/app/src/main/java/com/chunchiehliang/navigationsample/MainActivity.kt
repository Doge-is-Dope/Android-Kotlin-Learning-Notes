package com.chunchiehliang.navigationsample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.chunchiehliang.navigationsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.searchFragment,
                R.id.shoppingFragment,
                R.id.userFragment
            ),
        )
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.toolbar.isVisible = destination.id != R.id.homeFragment
        }
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        binding.bottomNav?.setupWithNavController(navController)
        binding.navigationRail?.setupWithNavController(navController)
    }
}