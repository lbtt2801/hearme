package com.lbtt2801.hearme

import com.lbtt2801.hearme.R
import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import com.lbtt2801.hearme.databinding.ActivityMainBinding
import com.lbtt2801.hearme.viewmodel.ArtistViewModel
import com.lbtt2801.hearme.viewmodel.CategoriesViewModel
import com.lbtt2801.hearme.viewmodel.EmailViewModel
import com.lbtt2801.hearme.viewmodel.UserViewModel
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModelArtist: ArtistViewModel by viewModels()
    private val viewModelCategory: CategoriesViewModel by viewModels()
    private val viewModelEmail: EmailViewModel by viewModels()
    private val viewModelUser: UserViewModel by viewModels()

    lateinit var email: String

    var checkInHome = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.transparent)
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = true
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        viewModelArtist.getListDataArtists()
        viewModelCategory.getListDataCategories()
        viewModelEmail.selectedItem.observe(this, Observer {
            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
            email = it
        })
        viewModelUser.getListDataUser()

//        UserData.data() // khoi tao du lieu

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHostFragment.navController

        customToolbar("GONE")
        showBottomNav("GONE")

        binding.bottomNavView.setOnItemSelectedListener { onClickItemBottomNav(it) }
        binding.toolBar.setOnMenuItemClickListener() {
            when (it.itemId) {
                R.id.item_search -> {
                    true
                }
                R.id.item_notification -> {
                    navController.navigate(R.id.action_item_nav_home_to_notificationFragment)
                    true
                }
                R.id.item_filter -> {
                    true
                }
                R.id.item_more_circle -> {
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun onBackPressed() {
        if (!checkInHome)
            super.onBackPressed()
        else showDialogBox()
    }

    private fun showDialogBox() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_back)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        val btnYes: TextView = dialog.findViewById(R.id.btnYesDialog)
        val btnNo: TextView = dialog.findViewById(R.id.btnNoDialog)

        btnYes.setOnClickListener {
            moveTaskToBack(true);
            exitProcess(-1)
        }

        btnNo.setOnClickListener {
            dialog.dismiss()
        }

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
        userName: String? = null,
        backgroundColor: Int = R.color.transparent,
        navIcon: Drawable? = null,
        showIcMore: Boolean = false,
        showIcFilter: Boolean = false,
        showIcSearch: Boolean = false,
        showIcNotification: Boolean = false
    ) {
        //Toolbar visibility
        when (isVisible.lowercase()) {
            "visible" -> binding.toolBar.visibility = View.VISIBLE
            "invisible" -> binding.toolBar.visibility = View.INVISIBLE
            else -> binding.toolBar.visibility = View.GONE
        }

        //Toolbar title
        if (title != null) {
            binding.toolBarTitle.isVisible = true
            binding.containerTitleForHome.isVisible = false
            binding.toolBarTitle.text = title
        } else if (userName != null) {
            binding.toolBarTitle.isVisible = false
            binding.containerTitleForHome.isVisible = true
            binding.textUserName.text = userName
        } else {
            binding.toolBarTitle.isVisible = false
            binding.containerTitleForHome.isVisible = false
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
            binding.toolBar.navigationIcon = navIcon
        } else {
            binding.toolBar.navigationIcon = null
        }

        binding.toolBar.menu.findItem(R.id.item_more_circle).isVisible = showIcMore
        binding.toolBar.menu.findItem(R.id.item_filter).isVisible = showIcFilter
        binding.toolBar.menu.findItem(R.id.item_search).isVisible = showIcSearch
        binding.toolBar.menu.findItem(R.id.item_notification).isVisible = showIcNotification
    }

    fun showBottomNav(isVisible: String) {
        when (isVisible.lowercase()) {
            "visible" -> binding.bottomNavView.visibility = View.VISIBLE
            "invisible" -> binding.bottomNavView.visibility = View.INVISIBLE
            else -> binding.bottomNavView.visibility = View.GONE
        }
    }

    fun changeSizeBitmap(image: Int, width: Int, height: Int): Drawable {
        val drawable = resources.getDrawable(image)
        val bitmap: Bitmap = (drawable as BitmapDrawable).bitmap
        val newDrawable: Drawable =
            BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap, width, height, true))
        return newDrawable
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