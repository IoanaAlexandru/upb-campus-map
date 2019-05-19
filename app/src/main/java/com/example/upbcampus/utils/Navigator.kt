package com.example.upbcampus.utils

import com.example.upbcampus.model.*

class Navigator (
    val path: List<Node>
){
    var dirs = mutableListOf<Direction>()

    fun getDirections() : List<Direction> {
        var i = 0
        while (i < path.size) {
            if (i == 0) {
                dirs.add(LocationDirection(path[i], Heading.UNKNOWN))
                i++
                continue
            }

            when (val node = path[i]) {
                is Room -> {
                    val lastNode = dirs.removeAt(dirs.lastIndex)
                    if (lastNode is IntersectionDirection)
                        dirs.add(LocationDirection(node, lastNode.info))
                    else if (lastNode is WalkDirection) {
                        dirs.add(lastNode)
                        dirs.add(LocationDirection(node, Heading.FORWARD))
                    }
                }

                is Intersection -> {
                    val heading = node.getHeading(path[i-1].id, path[i+1].id)
                    if (heading == Heading.FORWARD) {
                        if (dirs[dirs.lastIndex] !is WalkDirection)
                            dirs.add(WalkDirection(path[i-1], path[i+1]))
                    }
                    else {
                        if (dirs[dirs.lastIndex] is IntersectionDirection)
                            dirs.add(WalkDirection(path[i-1], node))
                        dirs.add(IntersectionDirection(path[i - 1], path[i + 1], heading))
                    }
                }

                is Stairs -> {
                    if (dirs[dirs.lastIndex] !is StairsDirection) {
                        while (path[i+1] is Stairs) {
                            i++
                        }
                        dirs.add(StairsDirection(node, path[i]))
                    }
                }

                is Elevator -> {
                    if (dirs[dirs.lastIndex] !is ElevatorDirection) {
                        while (path[i+1] is Elevator) {
                            i++
                        }
                        dirs.add(ElevatorDirection(node, path[i]))
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