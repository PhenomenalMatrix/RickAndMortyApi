package com.orozbek.rickandmortyapi.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.orozbek.rickandmortyapi.R
import com.orozbek.rickandmortyapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        initNav()
    }

    private fun initNav() {
        navController = findNavController(R.id.main_container)
        binding.bottomNav.setupWithNavController(navController)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.characterFragment,R.id.locationFragment,R.id.episodeFragment,R.id.searchFragment))
        setupActionBarWithNavController(navController,appBarConfiguration)
    }

}