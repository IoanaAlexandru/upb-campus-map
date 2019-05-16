package com.example.upbcampus.mapmodel

const val COST : Int = 1

class Pathfinder (val source: Node, val target: Node) {
    private var mapSize = UPBMap.nodes.size
    private var dist = IntArray(mapSize) { Int.MAX_VALUE }
    private var sptSet = BooleanArray(mapSize)
    private var parent = IntArray(mapSize) { -1 }

    fun minDistance() : Int {
        var min = Int.MAX_VALUE
        var minIndex = 0

        for (i in 0 until mapSize) {
            if (!sptSet[i] && dist[i] <= min) {
                min = dist[i]
                minIndex = i
            }
        }

        return minIndex
    }

    fun dijkstra() {
        dist[source.id] = 0

        for (count in 0 until mapSize - 1) {
            val u = minDistance()
            sptSet[u] = true

            val uNeighbours = UPBMap.nodes[u]?.getNeighbours()
            for (v in uNeighbours!!) {
                if (!sptSet[v.id] &&
                    dist[u] != Int.MAX_VALUE &&
                    dist[u] + COST < dist[v.id])
                {
                    dist[v.id] = dist[u] + COST
                    parent[v.id] = u
                }

                //TODO: decide about this
                //if (v.id == target.id)
                //   return
            }
        }
    }

    fun getPath() : MutableList<Node?> {
        dijkstra()

        val path = mutableListOf<Node?>()
        var node : Node? = target
        while (node != source) {
            path.add(0, node)
            node = UPBMap.nodes[parent[node!!.id]]
        }

        return path
    }
}
