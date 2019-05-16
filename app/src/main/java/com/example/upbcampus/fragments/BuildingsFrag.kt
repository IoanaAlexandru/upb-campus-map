package com.example.upbcampus.fragments

import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.upbcampus.utils.CustomAdapter
import com.example.upbcampus.R
import com.example.upbcampus.utils.DataModel
import java.util.*


class BuildingsFrag : Fragment() {
    var nameArray = arrayOf("EC", "ED")
    var drawableArray = arrayOf(R.drawable.ec, R.drawable.ed)
    var id_ = arrayOf(0, 1)

    private var adapter: RecyclerView.Adapter<*>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var recyclerView: RecyclerView? = null
    private var data: ArrayList<DataModel>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        /* The Layout of this Fragment is buildings*/
        val rootView = inflater.inflate(R.layout.buildings, container, false)

        /* RecyclerView is used along with CustomAdapter */
        recyclerView = rootView.findViewById<View>(R.id.my_recycler_view) as RecyclerView
        recyclerView!!.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this.requireContext())
        recyclerView!!.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        recyclerView!!.itemAnimator = DefaultItemAnimator()

        /* Fill the ArrayList used in CustomAdapter */
        data = ArrayList()
        for (i in 0 until nameArray.size) {
            data!!.add(
                DataModel(
                    nameArray[i],
                    id_[i],
                    drawableArray[i]
                )
            )
        }

        /* Set the adapter and the recyclerView */
        adapter = CustomAdapter(data!!, context!!)
        recyclerView!!.adapter = adapter

        return rootView
    }
}