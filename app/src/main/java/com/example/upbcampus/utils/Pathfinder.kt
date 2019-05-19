package com.example.upbcampus.utils

import android.util.Log
import com.example.upbcampus.model.Node
import com.example.upbcampus.model.UPBMap

const val COST : Int = 1

class Pathfinder (private val source: Node, private val target: Node) {
    private var mapSize = UPBMap.nodesById.size + 1
    private var dist = IntArray(mapSize) { Int.MAX_VALUE }
    private var sptSet = BooleanArray(mapSize) { false }
    private var parent = IntArray(mapSize) { -1 }

    private fun minDistance() : Int {
        var min = Int.MAX_VALUE
        var minIndex = 1

        for (i in 1 until mapSize) {
            if (!sptSet[i] && dist[i] < min) {
                min = dist[i]
                minIndex = i
            }
        }

        return minIndex
    }

    private fun dijkstra() {
        dist[source.id] = 0

        for (count in 1 until mapSize) {
            val u = minDistance()
            sptSet[u] = true

            UPBMap.nodesById[u]?.getNeighbours()?.forEach { neighbour ->
                if (!sptSet[neighbour.id] &&
                    dist[u] != Int.MAX_VALUE &&
                    dist[u] + COST < dist[neighbour.id])
                {
                    dist[neighbour.id] = dist[u] + COST
                    parent[neighbour.id] = u
                }

                //TODO: decide about this
                //if (neighbour.id == target.id)
                //   return
            }
        }
    }

    fun getPath() : MutableList<Node> {
        dijkstra()

        val path = mutableListOf<Node>()
        var node = target
        while (node != source) {
            path.add(0, node)
            node = UPBMap.nodesById[parent[node.id]] ?: return path
        }
        path.add(0, source)

        path.forEach{ Log.d("[Pathfinder]", it.toString()) }
        return path
    }
}
