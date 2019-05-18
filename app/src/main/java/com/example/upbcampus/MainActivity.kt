package com.example.upbcampus

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.SearchView
import android.widget.TextView
import com.example.upbcampus.fragments.BuildingsFrag
import com.example.upbcampus.fragments.FavouritesFrag
import com.example.upbcampus.fragments.HistoryFrag
import com.example.upbcampus.model.Room
import com.example.upbcampus.model.UPBMap
import com.example.upbcampus.model.UPBUser
import com.example.upbcampus.utils.App
import java.util.*
import android.widget.Toast



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
        App.mActivity = this
        setContentView(R.layout.activity_main)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        loadFragByTag(FRAG_BUILIDINGS)
        searchView = findViewById(R.id.sv_location)
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                val location = searchView?.query.toString()
                UPBUser.search(location, Calendar.getInstance().time)
                (getFragByTag(FRAG_HISTORY) as HistoryFrag).notify()
                displayRoomInfo(UPBMap.roomsByName[location.toUpperCase()], App.mActivity, layoutInflater)
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                return false
            }
        })
        val navigation: BottomNavigationView = findViewById(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    fun displayRoomInfo(
        room: Room?,
        ctx: Context?,
        layoutInflater: LayoutInflater?
    ) {
        if (room == null) {
            val toast = Toast.makeText(this, "Search term not found", Toast.LENGTH_LONG)
            toast.show()
            return
        }

        val alertDialog = AlertDialog.Builder(ctx)
        val view = layoutInflater?.inflate(R.layout.custompopup, null)
        val text = view?.findViewById(R.id.room_info) as TextView

        var saved = UPBUser.isInFavourites(room.name)
        val favouritesButton = view.findViewById(R.id.save_button) as Button
        updateFavouritesButton(favouritesButton, saved)
        favouritesButton.setOnClickListener {
            if (saved) {
                UPBUser.removeFromFavourites(room.name)
                (getFragByTag(FRAG_FAVOURITES) as FavouritesFrag).notify()
            } else {
                UPBUser.addToFavourites(room.name)
                (getFragByTag(FRAG_FAVOURITES) as FavouritesFrag).notify()
            }
            saved = !saved
            updateFavouritesButton(favouritesButton, saved)

            alertDialog.setView(view)
        }

        val startButton = view.findViewById(R.id.start_button) as Button
        startButton.setOnClickListener {
            UPBUser.src = room
            val toast = Toast.makeText(this, "${room.name} set as source", Toast.LENGTH_LONG)
            toast.show()
        }

        val navigateButton = view.findViewById(R.id.navigate_button) as Button
        navigateButton.setOnClickListener {
            UPBUser.dst = room
            val directions = UPBMap.navigate(UPBUser.src, room)
            // TODO display directions
            directions.forEach { Log.d(this::class.java.simpleName, it.toString()) }
        }

        text.text = "Name: ${room.name}\n\n" +
                "Building: ${room.building}\n\n" +
                "Floor: ${room.floor}\n\n" +
                "Type: ${room::class.java.simpleName}"

        /* Display dialog */
        val dialog = alertDialog.setView(view)
        dialog.create()
            .show()
    }

    private fun updateFavouritesButton(favouritesButton: Button, saved: Boolean) {
        favouritesButton.setText(if (saved) R.string.unsave else R.string.save)
        val icon = if (saved) R.drawable.baseline_favorite_24dp else R.drawable.ic_baseline_favorite_border_24px
        favouritesButton.setCompoundDrawablesWithIntrinsicBounds(
            null,
            ContextCompat.getDrawable(this, icon),
            null,
            null
        )
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

    override fun onDestroy() {
        super.onDestroy()
        UPBUser.saveData()
    }
}
