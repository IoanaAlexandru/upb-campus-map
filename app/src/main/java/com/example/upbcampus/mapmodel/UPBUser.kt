package com.example.upbcampus.mapmodel

import android.util.Log
import com.example.upbcampus.utils.App
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.util.*

const val HISTORY_FILE = "history.json"

private class Query(var query: String, var lastSearched: Date, var frequency: Int = 1) : Comparable<Query> {
    override fun compareTo(other: Query): Int {
        return query.compareTo(other.query)
    }
}

// Singleton with lazy initialisation
object UPBUser {
    private val queries : MutableMap<String, Query>
    private val queriesByLastSearched = TreeSet<Query>(compareBy { si -> si.lastSearched })
    private val queriesByFrequency = TreeSet<Query>(compareBy { si -> si.frequency })

    val historyList: MutableList<String>

    init {
        val gson = Gson()

        val historyFile = File(App.mFilesDir, HISTORY_FILE)
        val reader = FileReader(historyFile)

        val queryListType = object : TypeToken<List<Query>>() {}.type
        val savedQueries = gson.fromJson<List<Query>>(reader, queryListType)

        queries = savedQueries.map { query -> query.query to query}.toMap().toMutableMap()
        historyList = queries.keys.toMutableList()
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
    }

    fun saveData() {
        val gson = Gson()
        val queriesJson = gson.toJson(queries.values)

        try {
            val historyFile = File(App.mFilesDir, HISTORY_FILE)
            val writer = FileWriter(historyFile)
            writer.append(queriesJson)
            writer.flush()
            writer.close()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(this::class.java.simpleName, "Couldn't save history to storage")
        }
    }
}