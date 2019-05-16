package com.example.upbcampus.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.upbcampus.R
import com.example.upbcampus.utils.CustomAdapter
import com.example.upbcampus.utils.DataModel
import java.util.*

class BuildingsFrag : Fragment() {
    private val nameArray = arrayOf("EC", "ED")
    private val drawableArray = arrayOf(R.drawable.ec, R.drawable.ed)
    private val id = arrayOf(0, 1)

    private lateinit var adapter: RecyclerView.Adapter<*>
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var data: ArrayList<DataModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /* The Layout of this Fragment is buildings*/
        val rootView = inflater.inflate(R.layout.buildings, container, false)

        /* RecyclerView is used along with CustomAdapter */
        recyclerView = rootView.findViewById<View>(R.id.my_recycler_view) as RecyclerView
        recyclerView.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this.requireContext())
        recyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        recyclerView.itemAnimator = DefaultItemAnimator()

        /* Fill the ArrayList used in CustomAdapter */
        data = ArrayList()
        for (i in 0 until nameArray.size) {
            data.add(
                DataModel(
                    nameArray[i],
                    id[i],
                    drawableArray[i]
                )
            )
        }

        /* Set the adapter and the recyclerView */
        adapter = CustomAdapter(data, context!!)
        recyclerView.adapter = adapter

        return rootView
    }
}