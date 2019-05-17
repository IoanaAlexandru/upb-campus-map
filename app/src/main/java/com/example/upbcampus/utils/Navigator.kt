package com.example.upbcampus.utils

import com.example.upbcampus.mapmodel.*

class Navigator (
    val path: List<Node>
){
    var dirs = mutableListOf<Direction>()

    fun getDirections() : List<Direction> {
        var i = 0
        while (i < path.size) {
            val node = path[i]
            when (node) {
                is Room -> {
                    if (i == 0) dirs.add(LocationDirection(node, Heading.UNKNOWN))
                    else {
                        val lastNode = dirs.removeAt(dirs.lastIndex)
                        if (lastNode is IntersectionDirection)
                            dirs.add(LocationDirection(node, lastNode.info))
                    }
                }

                is Intersection -> {
                    val heading = node.getHeading(path[i-1].id, path[i+1].id)
                    if (heading == Heading.FORWARD) {
                        if (dirs[dirs.lastIndex] !is WalkDirection)
                            dirs.add(WalkDirection(path[i-1], path[i+1]))
                    }
                    else
                        dirs.add(IntersectionDirection(path[i-1], path[i+1], heading))
                }

                is Vertical -> {
                    if (dirs[dirs.lastIndex] !is VerticalDirection) {
                        while (path[i+1] is Vertical) {
                            i++
                        }
                        dirs.add(VerticalDirection(node, path[i]))
                    }
                }

                is Entrance -> {
                    dirs.add(EntranceDirection(node))
                }
            }
            i++
        }

        return dirs
    }
}