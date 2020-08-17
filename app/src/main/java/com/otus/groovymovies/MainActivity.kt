package com.otus.groovymovies

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity(), FilmListFragment.OnFilmClickListener {
    companion object {
        val TAG = MainActivity::class.java.simpleName
        const val KEY = "key"
    }

    private var items: ArrayList<FilmItem> = arrayListOf()
    private lateinit var navigation: BottomNavigationView
    private var menuItem = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        navigation.setOnNavigationItemSelectedListener {
            return@setOnNavigationItemSelectedListener when (it.itemId) {
                R.id.bnMain -> {
                    openListFragment()
                    true
                }
                R.id.bnFavorites -> {
                    openFavoriteFragment(items)
                    true
                }
                R.id.bnInviteFriend -> {
                    inviteFriend()

                    false
                }
                else -> false
            }
        }

        openListFragment()
    }

    private fun openListFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, FilmListFragment(), FilmListFragment.TAG)
            .commit()
    }

    private fun openDetailedFragment(filmItem: FilmItem) {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragmentContainer,
                FilmDetailedFragment.newInstance(filmItem),
                FilmDetailedFragment.TAG
            )
            .addToBackStack(FilmDetailedFragment.TAG)
            .commit()
    }

    private fun openFavoriteFragment(items: ArrayList<FilmItem>) {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragmentContainer,
                FavoriteListFragment.newInstance(items),
                FavoriteListFragment.TAG
            )
            .addToBackStack(FavoriteListFragment.TAG)
            .commit()
    }

    @Override
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            navigation.selectedItemId = R.id.bnMain
        } else {
            val dialog: Dialog = CustomDialog(this, object : CustomDialog.DialogClickListener {
                override fun confirmClickListener() {
                    finish()
                }

                override fun denyClickListener() {
                }
            })
            dialog.show()
        }
    }

    private fun inviteFriend() {
        val textMessage = resources.getString(R.string.invite_message)
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, textMessage)
        sendIntent.type = "text/plain"
        val title = resources.getString(R.string.inviteFriend)
        val chooser = Intent.createChooser(sendIntent, title)
        sendIntent.resolveActivity(packageManager)?.let {
            startActivity(chooser)
        }
    }

    override fun onDetailedClick(filmItem: FilmItem) {
        openDetailedFragment(filmItem)
    }

    override fun onFavoriteClick(filmItem: FilmItem): Boolean {
        return if (items.contains(filmItem)) {
            false
        } else {
            items.add(filmItem)
            true
        }
    }

}