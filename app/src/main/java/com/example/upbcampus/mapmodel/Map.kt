package com.example.upbcampus.mapmodel
import com.example.upbcampus.utils.App
import com.example.upbcampus.R
import com.example.upbcampus.utils.NodeDeserializer
import com.example.upbcampus.utils.Pathfinder
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken


// Singleton with lazy initialisation
object UPBMap {
    val nodes : Map<Int, Node>

    init {
        // parse data file to initialise nodes
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(Node::class.java, NodeDeserializer())
        val gson = gsonBuilder.create()

        val nodeListType = object : TypeToken<ArrayList<Node>>(){}.type
        val nodeJson = App.mResources?.openRawResource(R.raw.nodes)?.reader()
        val nodeList = gson.fromJson<List<Node>>(nodeJson, nodeListType)
        nodes = nodeList.map { node -> node.id to node }.toMap()
    }

    // TODO(Mark) pai nu fac eu?
    fun navigate(src: Node, dst: Node): List<Direction> {
        val pathfinder = Pathfinder(src, dst)

        return emptyList<Direction>()
    }
}