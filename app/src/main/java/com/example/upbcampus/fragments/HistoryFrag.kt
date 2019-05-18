package com.example.upbcampus.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.upbcampus.R
import java.util.*

import kotlin.collections.HashMap


private class Query(var query: String, var lastSearched: Date, var frequency: Int = 1) : Comparable<Query> {
    override fun compareTo(other: Query): Int {
        return query.compareTo(other.query)
    }
}

class HistoryFrag : Fragment() {
    private val queries = HashMap<String, Query>()
    private val queriesByLastSearched = TreeSet<Query>(compareBy { si -> si.lastSearched })
    private val queriesByFrequency = TreeSet<Query>(compareBy { si -> si.frequency })
    private var listAdapter : ArrayAdapter<String>? = null
    private lateinit var historyList : MutableList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.history, container, false)
        val listView = rootView.findViewById<View>(R.id.lv) as? ListView

        historyList = queries.keys.toMutableList()
        listAdapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, historyList)
        listView?.adapter = listAdapter
        listView?.transcriptMode = ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL
        listView?.isStackFromBottom = true

        listAdapter?.notifyDataSetChanged()


        return rootView
    }

    fun search(query: String, date: Date) {
        if (queries.contains(query)) {
            queries[query]?.apply {
                if (date > lastSearched) lastSearched = date
                frequency++
            }
        } else {
            queries[query] = Query(query, date)
        }

        historyList.add(query)

        //TODO: 1.resolve duplicates bug from historyList
        //TODO: 2.resolve crash when text is added in search bar BEFORE the creation of HistoryFrag()
        //TODO: 3.resolve bug of listview being erased AFTER leaving HistoryFrag()
        Log.d(this::class.java.simpleName, "[search] size of list is ${historyList.size}")

        listAdapter?.notifyDataSetChanged()
    }
}