package com.example.upbcampus.mapmodel

// Singleton with lazy initialisation
object UPBMap {
    val nodes = hashMapOf<Int, Node>()

    init {
        // TODO(Ioana)
        // parse data file to initialise nodes
    }

    // TODO(Mark) pai nu fac eu?
    fun navigate(src: Node, dst: Node): List<Direction> {
        val pathfinder = Pathfinder(src, dst)

        return emptyList<Direction>()
    }
}