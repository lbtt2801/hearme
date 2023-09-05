package com.lbtt2801.hearme

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.lbtt2801.hearme.data.UserData
import com.lbtt2801.hearme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        binding.lifecycleOwner = this

        UserData.data() // khoi tao du lieu

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHostFragment.navController

        customToolbar("GONE", null, R.color.transparent, null)
        showBottomNav("GONE")
    }

    fun customToolbar(
        isVisible: String,
        title: String? = null,
        backgroundColor: Int,
        navIcon: Int? = null
    ) {
        //Toolbar visibility
        when (isVisible) {
            "VISIBLE" -> {
                binding.toolBar.visibility = View.VISIBLE
            }

            "INVISIBLE" -> {
                binding.toolBar.visibility = View.INVISIBLE
            }

            else -> {
                binding.toolBar.visibility = View.GONE
            }
        }
        //Toolbar title
        if (title != null) {
            binding.toolBar.title = title
        }
        //Toolbar background
        binding.toolBar.setBackgroundColor(
            resources.getColor(
                backgroundColor,
                null
            )
        )
        //Toolbar nav icon
        if (navIcon != null) {
            binding.toolBar.setNavigationIcon(navIcon)
        }
    }

    fun showBottomNav(isVisible: String) {
        when (isVisible) {
            "VISIBLE" -> binding.navView.visibility = View.VISIBLE
            "INVISIBLE" -> binding.navView.visibility = View.INVISIBLE
            else -> binding.navView.visibility = View.GONE
        }
    }
}