package com.example.upbcampus

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.SearchView
import com.example.upbcampus.fragments.BuildingsFrag
import com.example.upbcampus.fragments.FavouritesFrag
import com.example.upbcampus.fragments.HistoryFrag
import java.util.*

class MainActivity : AppCompatActivity() {
    private var searchView: SearchView? = null

    val CLASS_NAME = this::class.java.simpleName

    val FRAG_FAVOURITES = "favourites_frag"
    val FRAG_HISTORY = "history_frag"
    val FRAG_BUILIDINGS = "buildings_frag"


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_history -> {
                loadFragByTag(FRAG_HISTORY)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favourites -> {
                loadFragByTag(FRAG_FAVOURITES)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_buildings -> {
                loadFragByTag(FRAG_BUILIDINGS)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        loadFragByTag(FRAG_BUILIDINGS)
        searchView = findViewById(R.id.sv_location)
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                val location = searchView?.query.toString()
                (getFragByTag(FRAG_HISTORY) as HistoryFrag).search(location, Calendar.getInstance().time)
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                return false
            }
        })
        val navigation: BottomNavigationView = findViewById(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun loadFragByTag(tag: String) {
        val frag = getFragByTag(tag)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_main, frag, tag)
            .commit()
    }

    private fun getFragByTag(tag: String): Fragment {
        var frag = supportFragmentManager.findFragmentByTag(tag)
        if (frag == null) {
            Log.d(CLASS_NAME, "$tag not found, creating a new one.")
            frag = when (tag) {
                FRAG_FAVOURITES -> FavouritesFrag()
                FRAG_HISTORY -> HistoryFrag()
                FRAG_BUILIDINGS -> BuildingsFrag()
                else -> Fragment()  // shouldn't happen
            }

            supportFragmentManager
                .beginTransaction()
                .add(frag, tag)
                .commit()
        } else {
            Log.d(CLASS_NAME, "$tag found.")
        }

        return frag
    }
}
