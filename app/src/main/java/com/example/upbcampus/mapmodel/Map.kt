package com.example.upbcampus.mapmodel

import android.util.Log
import com.example.upbcampus.R
import com.example.upbcampus.utils.App
import com.example.upbcampus.utils.NodeDeserializer
import com.example.upbcampus.utils.Pathfinder
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken


// Singleton with lazy initialisation
object UPBMap {
    // Nodes mapped by ID
    val nodesById: Map<Int, Node>
    // Nodes mapped by location (floor & building)
    val roomsByLocation = mutableMapOf<Pair<Int, Building>, MutableList<Room>>()

    init {
        // parse data file to initialise nodesById
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(Node::class.java, NodeDeserializer())
        val gson = gsonBuilder.create()

        val nodeListType = object : TypeToken<List<Node?>>() {}.type
        val nodeJson = App.mResources?.openRawResource(R.raw.nodes)?.reader()
        val nodeList = gson.fromJson<List<Node?>>(nodeJson, nodeListType)
        if (nodeList.contains(null))
            Log.e(this::class.java.simpleName, "Invalid JSON field detected")

        nodesById = nodeList.filterNotNull().map { node -> node.id to node }.toMap()
        nodeList.filterNotNull().forEach { node ->
            if (node is Room) {
                val key = Pair(node.floor, node.building)
                if (roomsByLocation[key] == null)
                    roomsByLocation[key] = mutableListOf(node)
                else
                    roomsByLocation[key]?.add(node)
            }
        }
    }

    // TODO(Mark) pai nu fac eu?
    fun navigate(src: Node, dst: Node): List<Direction> {
        val pathfinder = Pathfinder(src, dst)

        return emptyList<Direction>()
    }
}