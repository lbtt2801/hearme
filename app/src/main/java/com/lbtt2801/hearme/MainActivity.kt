package com.lbtt2801.hearme

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.lbtt2801.hearme.data.UserData
import com.lbtt2801.hearme.databinding.ActivityMainBinding
import com.lbtt2801.hearme.viewmodel.ArtistViewModel
import com.lbtt2801.hearme.viewmodel.EmailViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModelArtist: ArtistViewModel by viewModels()
    private val viewModelEmail: EmailViewModel by viewModels()

    lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        viewModelArtist.getListDataArtists()
        viewModelEmail.selectedItem.observe(this, Observer {
            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
            email = it
        })

        UserData.data() // khoi tao du lieu

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHostFragment.navController

        binding.toolBar.setNavigationOnClickListener() {
            navHostFragment.findNavController().popBackStack()
        }

        customToolbar("GONE", null, R.color.transparent, null)
        showBottomNav("GONE")
    }

    private fun onClickItemBottomNav(id: MenuItem) = when (id.itemId) {
        R.id.navigation_home -> {
            navController.navigate(R.id.navigation_home)
            true
        }

        R.id.navigation_explore -> {
            navController.navigate(R.id.navigation_explore)
            true
        }

        R.id.navigation_library -> {
            navController.navigate(R.id.navigation_library)
            true
        }

        R.id.navigation_profile -> {
            navController.navigate(R.id.navigation_library)
            true
        }
        else -> false
    }

    fun customToolbar(
        isVisible: String,
        title: String? = null,
        backgroundColor: Int,
        navIcon: Int? = null,
        showIcMore: Boolean = false,
        showIcSearch: Boolean = false
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
        binding.toolBar.menu.findItem(R.id.item_more_circle).isVisible = showIcMore
        binding.toolBar.menu.findItem(R.id.item_search).isVisible = showIcSearch
    }

    fun showBottomNav(isVisible: String) {
        when (isVisible) {
            "VISIBLE" -> binding.navView.visibility = View.VISIBLE
            "INVISIBLE" -> binding.navView.visibility = View.INVISIBLE
            else -> binding.navView.visibility = View.GONE
        }
    }
}