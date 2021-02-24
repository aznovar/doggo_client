package com.ru.appdoggo.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.ru.appdoggo.App
import com.ru.appdoggo.R
import com.ru.appdoggo.domain.type.None
import com.ru.appdoggo.presentation.viewmodel.AccountViewModel
import com.ru.appdoggo.presentation.viewmodel.BottomNavigationViewModel
import com.ru.appdoggo.presentation.viewmodel.FriendsViewModel
import com.ru.appdoggo.ui.core.BaseActivity
import com.ru.appdoggo.ui.core.BaseFragment
import com.ru.appdoggo.ui.core.ext.onFailure
import com.ru.appdoggo.ui.core.ext.onSuccess
import com.ru.appdoggo.ui.core.inTransaction
import com.ru.appdoggo.ui.friends.FriendsFragment
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.navigation.*
import javax.inject.Inject

class ProfileActivity : AppCompatActivity() {

    open val contentId = R.layout.activity_navigation

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var accountViewModel: AccountViewModel

    private lateinit var friendsViewModel: FriendsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupContent()
        App.appComponent.inject(this)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.settings_button)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        accountViewModel = viewModel {
            onSuccess(logoutData, ::handleLogout)
        }

        btnLogout.setOnClickListener {
            accountViewModel.logout()
        }
        //todo произойдет временный запил контейнера с фрагментами, надо будет потом все привести к nav controller'у
        btnFriendsList.setOnClickListener {
            replaceFragment(FriendsFragment())
            closeDrawer()
        }
    }


    private fun setupContent() {
        setContentView(contentId)
    }

    private fun closeDrawer(animate: Boolean = true){
        drawerLayout.closeDrawer(navigationView, animate)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                if (drawerLayout.isDrawerOpen(navigationView)) {
                    drawerLayout.closeDrawer(navigationView)
                } else {
                    drawerLayout.openDrawer(navigationView)
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawer(navigationView)
        } else {
            super.onBackPressed()
        }
    }

    private fun handleLogout(none: None?) {
        showLogin(this)
    }

    inline fun <reified T : ViewModel> viewModel(body: T.() -> Unit): T {
        val vm = ViewModelProviders.of(this, viewModelFactory)[T::class.java]
        vm.body()
        return vm
    }

    private fun showLogin(context: Context) {
        context.startActivity<BaseActivity>()
    }

    private inline fun <reified T> Context.startActivity(
        newTask: Boolean = false,
        args: Bundle? = null
    ) {
        this.startActivity(Intent(this, T::class.java).apply {
            if (newTask) {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }
            putExtra("args", args)
        })

    }

    fun replaceFragment(fragment: BaseFragment) {
        supportFragmentManager.inTransaction {
            replace(R.id.fragmentContainer,fragment)
        }
    }
}