package com.example.upbcampus.model

import android.util.Log
import com.example.upbcampus.utils.App
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.util.*

const val HISTORY_FILE = "history.json"
const val FAVOURITES_FILE = "favourites.json"

private class Query(var query: String, var lastSearched: Date, var frequency: Int = 1) : Comparable<Query> {
    override fun compareTo(other: Query): Int {
        return query.compareTo(other.query)
    }
}

// Singleton with lazy initialisation
object UPBUser {
    private val queries: MutableMap<String, Query>
    private val queriesByLastSearched = TreeSet<Query>(compareBy { si -> si.lastSearched })
    private val queriesByFrequency = TreeSet<Query>(compareBy { si -> si.frequency })

    val historyList: MutableList<String>
    val favouritesList: MutableList<String>

    init {
        val gson = Gson()

        // Fetch history data from file
        val historyReader = getFileReader(HISTORY_FILE)

        val queryListType = object : TypeToken<List<Query>>() {}.type
        val savedQueries = gson.fromJson<List<Query>>(historyReader, queryListType) ?: mutableListOf()

        queries = savedQueries.map { query -> query.query to query }.toMap().toMutableMap()
        historyList = queries.keys.toMutableList()

        // Fetch favourites from file
        val favouritesReader = getFileReader(FAVOURITES_FILE)

        val stringListType = object : TypeToken<MutableList<String>>() {}.type
        favouritesList = gson.fromJson<MutableList<String>>(favouritesReader, stringListType) ?: mutableListOf()
    }

    private fun getFileReader(fileName: String): FileReader {
        val file = File(App.mFilesDir, fileName)
        if (!file.exists())
            file.createNewFile()
        return FileReader(file)
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

    fun addToFavourites(name: String) = favouritesList.add(name)

    fun removeFromFavourites(name: String) = favouritesList.remove(name)

    fun isInFavourites(name: String) = favouritesList.contains(name)

    fun saveData() {
        val gson = Gson()
        val queriesJson = gson.toJson(queries.values)
        val favouritesJson = gson.toJson(favouritesList)

        writeDataToFile(queriesJson, HISTORY_FILE)
        writeDataToFile(favouritesJson, FAVOURITES_FILE)
    }

    private fun writeDataToFile(data: String, file: String) {
        try {
            val historyFile = File(App.mFilesDir, file)
            val writer = FileWriter(historyFile)
            writer.append(data)
            writer.flush()
            writer.close()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(this::class.java.simpleName, "Couldn't write data to $file")
        }
    }
}