package com.anit.goodweather

import android.annotation.SuppressLint

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    SearchView.OnQueryTextListener {

    private val router: IRouter = Router(this, R.id.container_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }


    override fun onResume() {
        super.onResume()
        router.startWeather()
        nav_view.setCheckedItem(nav_view.menu.getItem(0))
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(this)

        return true
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        Toast.makeText(this, "Click search $query", Toast.LENGTH_SHORT).show()
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {
        Toast.makeText(this, "search: $newText", Toast.LENGTH_SHORT).show()
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.action_search -> {
                Snackbar.make(fab, "Action search", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                true
            }
            R.id.action_add -> {
                Snackbar.make(fab, "Action add", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }


    @SuppressLint("RestrictedApi")
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_weather -> {
                router.startWeather()
                fab.visibility = View.VISIBLE
            }
            R.id.nav_feedback -> {
                router.startFeedback()
                fab.visibility = View.GONE
            }
            R.id.nav_sensor -> {
                router.startSensor()
                fab.visibility = View.GONE
            }
            R.id.nav_about -> {
                router.startAbout()
                fab.visibility = View.GONE
            }
        }

        item.isChecked = true
        title = item.title

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}


