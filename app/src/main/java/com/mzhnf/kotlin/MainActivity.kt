package com.mzhnf.kotlin


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.mzhnf.travel_android_kotlin.R
import com.mzhnf.travel_android_kotlin.databinding.ActivityMainBinding
import github.com.st235.lib_expandablebottombar.navigation.ExpandableBottomBarNavigationUI

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        ExpandableBottomBarNavigationUI.setupWithNavController(binding.expandableBottomBar,navController)
    }
}