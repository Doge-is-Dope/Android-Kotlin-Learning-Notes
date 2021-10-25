package com.chunchiehliang.navigationsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.chunchiehliang.navigationsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
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
        binding.apply {
            toolbar.setupWithNavController(navController, appBarConfiguration)
            bottomNav.setupWithNavController(navController)
        }
    }
}