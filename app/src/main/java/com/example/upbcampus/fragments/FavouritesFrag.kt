package com.example.upbcampus.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.upbcampus.R

class FavouritesFrag : Fragment() {

    private var listAdapter : ArrayAdapter<String>? = null
    var favouritesList : MutableList<String> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_favourite, container, false)
        val listView = rootView.findViewById<View>(R.id.lv_fav) as? ListView

        listAdapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, favouritesList)
        listView?.adapter = listAdapter
        listView?.transcriptMode = ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL
        listView?.isStackFromBottom = true

        listAdapter?.notifyDataSetChanged()

        return rootView
    }

    fun getList() : MutableList<String> {
        return  favouritesList
    }
}