package com.example.upbcampus.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView

import com.example.upbcampus.R
import com.example.upbcampus.model.Direction
import com.example.upbcampus.model.UPBMap
import com.example.upbcampus.model.UPBUser
import com.example.upbcampus.utils.*
import java.util.ArrayList

class NavigateFrag : Fragment() {

    private val directions = ArrayList<Direction>()
    private val image = ArrayList<Int>()
    private val id = ArrayList<Int>()
    private lateinit var data: ArrayList<NavDataModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_navigate, container, false)
        val listView = rootView.findViewById(R.id.nav_lv) as? ListView
        listView?.transcriptMode = ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL
        listView?.isStackFromBottom = true

        data = ArrayList()

        /* Complete ListView with direction texts and its images */
        UPBMap.navigate(UPBUser.src, UPBUser.dst ?: return rootView).forEach { directions.add(it) }

        for (i in 0 until directions.size) {
            image.add(directions[i].getImage())
            id.add(i)
        }

        for (i in 0 until id.size) {
            data.add(
                NavDataModel(
                    directions[i].toString(),
                    id[i],
                    image[i]
                )
            )
        }

        val customAdapter = NavigateAdapter(data, context!!)
        listView!!.adapter = customAdapter

        return rootView
    }
}
