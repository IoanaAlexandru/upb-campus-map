package com.example.upbcampus.mapmodel

// Singleton with lazy initialisation
object UPBMap {
    private val nodes = emptyList<Node>()

    init {
        // TODO(Ioana)
        // parse data file to initialise nodes
    }

    // TODO(Mark)
    fun navigate(src: Node, dst: Node): List<Direction> = emptyList()
}