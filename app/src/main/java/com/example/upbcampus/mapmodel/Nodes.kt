package com.example.upbcampus.mapmodel

import com.example.upbcampus.R
import com.example.upbcampus.utils.*

enum class Building { EC, ED, UNKNOWN }

enum class Heading { FORWARD, BACK, RIGHT, LEFT, UNKNOWN }

abstract class Node(
    val id: Int, val name: String, val floor: Int, val building: Building
) {
    abstract fun getNeighbours(): List<Node>
}

class Entrance(
    id: Int, name: String, floor: Int, building: Building,
    val from: Int?, val to: Int
) :
    Node(id, name, floor, building) {
    override fun getNeighbours(): List<Node> {
        return mutableListOf(from, to).mapNotNull { UPBMap.nodesById[it] }
    }
}

abstract class Vertical(
    id: Int, name: String, floor: Int, building: Building,
    neighbor: Int, val up: Int?, val down: Int?
) :
    Node(id, name, floor, building)

class Stairs(
    id: Int, name: String, floor: Int, building: Building,
    neighbor: Int, up: Int?, down: Int?
) :
    Vertical(id, name, floor, building, neighbor, up, down) {
    override fun getNeighbours(): List<Node> {
        return mutableListOf(up, down).mapNotNull { UPBMap.nodesById[it] }
    }
}

class Elevator(
    id: Int, name: String, floor: Int, building: Building,
    neighbor: Int, up: Int?, down: Int?
) :
    Vertical(id, name, floor, building, neighbor, up, down) {
    override fun getNeighbours(): List<Node> {
        return mutableListOf(up, down).mapNotNull { UPBMap.nodesById[it] }
    }
}

class Intersection(
    id: Int, name: String, floor: Int, building: Building,
    val north: Int?, val south: Int?, val east: Int?, val west: Int?
) :
    Node(id, name, floor, building) {
    override fun getNeighbours(): List<Node> {
        return mutableListOf(north, east, south, west).mapNotNull { UPBMap.nodesById[it] }
    }

    fun getHeading(src: Int, dst: Int): Heading {
        when (src == north) {
            dst == north -> return Heading.RIGHT
            dst == east -> return Heading.LEFT
            dst == south -> return Heading.FORWARD
            dst == west -> return Heading.RIGHT
        }

        when (src == east) {
            dst == north -> return Heading.RIGHT
            dst == east -> return Heading.RIGHT
            dst == south -> return Heading.LEFT
            dst == west -> return Heading.FORWARD
        }

        when (src == south) {
            dst == north -> return Heading.FORWARD
            dst == east -> return Heading.RIGHT
            dst == south -> return Heading.RIGHT
            dst == west -> return Heading.LEFT
        }

        when (src == west) {
            dst == north -> return Heading.LEFT
            dst == east -> return Heading.FORWARD
            dst == south -> return Heading.RIGHT
            dst == west -> return Heading.RIGHT
        }

        return Heading.UNKNOWN
    }
}

abstract class Room(
    id: Int, name: String, floor: Int, building: Building,
    val neighbor: Int, val coords: Pair<Float, Float>
) :
    Node(id, name, floor, building) {
    override fun getNeighbours(): List<Node> {
        return mutableListOf<Int?>(neighbor).mapNotNull { UPBMap.nodesById[it] }
    }
}

class LectureHall(
    id: Int, name: String, floor: Int, building: Building,
    neighbor: Int, coords: Pair<Float, Float>
) :
    Room(id, name, floor, building, neighbor, coords)

class Laboratory(
    id: Int, name: String, floor: Int, building: Building,
    neighbor: Int, coords: Pair<Float, Float>
) :
    Room(id, name, floor, building, neighbor, coords)

class Restroom(
    id: Int, name: String, floor: Int, building: Building,
    neighbor: Int, coords: Pair<Float, Float>
) :
    Room(id, name, floor, building, neighbor, coords)

class Classroom(
    id: Int, name: String, floor: Int, building: Building,
    neighbor: Int, coords: Pair<Float, Float>
) :
    Room(id, name, floor, building, neighbor, coords)

class Office(
    id: Int, name: String, floor: Int, building: Building,
    neighbor: Int, coords: Pair<Float, Float>
) :
    Room(id, name, floor, building, neighbor, coords)

class Store(
    id: Int, name: String, floor: Int, building: Building,
    neighbor: Int, coords: Pair<Float, Float>
) :
    Room(id, name, floor, building, neighbor, coords)

class UnknownRoom(
    id: Int, name: String, floor: Int, building: Building,
    neighbor: Int, coords: Pair<Float, Float>
) :
    Room(id, name, floor, building, neighbor, coords)