package com.example.upbcampus.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.upbcampus.R
import com.example.upbcampus.model.UPBMap
import com.example.upbcampus.model.UPBUser
import com.example.upbcampus.utils.App

class HistoryFrag : Fragment() {
    private var listAdapter: ArrayAdapter<String>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.history, container, false)
        val listView = rootView.findViewById<View>(R.id.lv) as? ListView

        listAdapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, UPBUser.historyList)
        listView?.adapter = listAdapter
        listView?.transcriptMode = ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL
        listView?.isStackFromBottom = true

        listView?.setOnItemClickListener { _, _, position, _ ->
            val selection = listView.getItemAtPosition(position) as String
            App.mActivity?.displayRoomInfo(UPBMap.roomsByName[selection.toUpperCase()], context, inflater)
        }

        listAdapter?.notifyDataSetChanged()

        return rootView
    }

    fun notify() = listAdapter?.notifyDataSetChanged()
}