package com.example.upbcampus.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.history, container, false)
        val listView = rootView.findViewById<View>(R.id.lv) as? ListView

        // TODO fill list
//        var historyList = queries.keys.toList()
//        var sItems = arrayOf("one", "two", "three")
//        var listAdapter = ArrayAdapter(activity, android.R.layout.simple_list_item_1, sItems)
//        listView?.adapter = listAdapter
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

        // TODO notify adapter
    }
}