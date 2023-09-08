package com.lbtt2801.hearme

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.fragment.findNavController
import com.lbtt2801.hearme.data.UserData
import com.lbtt2801.hearme.databinding.ActivityMainBinding
import com.lbtt2801.hearme.viewmodel.ArtistViewModel
import com.lbtt2801.hearme.viewmodel.EmailViewModel
import com.lbtt2801.hearme.viewmodel.UserViewModel
import java.lang.Math.round

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModelArtist: ArtistViewModel by viewModels()
    private val viewModelEmail: EmailViewModel by viewModels()
    private val viewModelUser: UserViewModel by viewModels()

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
        viewModelUser.getListDataUser()

//        UserData.data() // khoi tao du lieu

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHostFragment.navController

        customToolbar("GONE", null, R.color.transparent, null)
        showBottomNav("GONE")

        binding.bottomNavView.setOnItemSelectedListener { onClickItemBottomNav(it) }
    }

    private fun onClickItemBottomNav(it: MenuItem) = when (it.itemId) {
        R.id.item_nav_home -> {
            navController.navigate(R.id.item_nav_home)
            true
        }

        R.id.item_nav_explore -> {
            navController.navigate(R.id.item_nav_explore)
            true
        }

        R.id.item_nav_library -> {
            navController.navigate(R.id.item_nav_library)
            true
        }

        R.id.item_nav_profile -> {
            navController.navigate(R.id.item_nav_profile)
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
        showIcFilter: Boolean = false,
        showIcSearch: Boolean = false
    ) {
        //Toolbar visibility
        when (isVisible) {
            "VISIBLE" -> binding.toolBar.visibility = View.VISIBLE
            "INVISIBLE" -> binding.toolBar.visibility = View.INVISIBLE
            else -> binding.toolBar.visibility = View.GONE
        }

        //Toolbar title
        if (title != null)
            binding.toolBar.title = title

        //Toolbar background
        binding.toolBar.setBackgroundColor(
            resources.getColor(
                backgroundColor,
                null
            )
        )

        //Toolbar nav icon
        if (navIcon != null)
            binding.toolBar.setNavigationIcon(navIcon)

        binding.toolBar.menu.findItem(R.id.item_more_circle).isVisible = showIcMore
        binding.toolBar.menu.findItem(R.id.item_filter).isVisible = showIcFilter
        binding.toolBar.menu.findItem(R.id.item_search).isVisible = showIcSearch
    }

    fun showBottomNav(isVisible: String) {
        when (isVisible.lowercase()) {
            "visible" -> binding.bottomNavView.visibility = View.VISIBLE
            "invisible" -> binding.bottomNavView.visibility = View.INVISIBLE
            else -> binding.bottomNavView.visibility = View.GONE
        }
    }

    fun showSnack(view: View, text: String) {
        val snack = Snackbar.make(
            view,
            text,
            Snackbar.LENGTH_SHORT
        )
        snack.show()
    }

    fun protectedPhone(phone: String): String {
        var editPhone = ""
        for (item in phone.indices) {
            if (item in 5..10)
                editPhone += "*"
            else
                editPhone += phone[item]
        }
        return editPhone
    }

    fun protectedEmail(email: String): String {
        var edtEmail = ""
        val splitAt = email.split("@")
        val str1 = splitAt[0]
        val str2 = splitAt[1]
        val len = str1.length
        var count = 0

        val int: Int = when (len) {
            in 6..8 -> {
                2
            }

            else -> {
                3
            }
        }
        for (item in str1.indices) {
            if (count < int) {
                edtEmail += email[item]
                count++
                continue
            }
            if (item < len - int)
                edtEmail += "*"
            else
                edtEmail += email[item]
        }

        return edtEmail.plus("@$str2")
    }
}