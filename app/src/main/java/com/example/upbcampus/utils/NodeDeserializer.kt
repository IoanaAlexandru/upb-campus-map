package com.example.upbcampus.utils

import com.example.upbcampus.mapmodel.*
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

const val ENTRANCE = "Entrance"
const val STAIRS = "Stairs"
const val ELEVATOR = "Elevator"
const val INTERSECTION = "Intersection"
const val LECTURE_HALL = "LectureHall"
const val LABORATORY = "Laboratory"
const val RESTROOM = "Restroom"
const val CLASSROOM = "Classroom"
const val OFFICE = "Office"
const val STORE = "Store"
const val TYPE = "type"
const val ID = "id"
const val NAME = "name"
const val FLOOR = "floor"
const val BUILDING = "building"
const val ED = "ED"
const val EC = "EC"
const val NEIGHBOR = "neighbor"
const val UP = "up"
const val DOWN = "down"
const val FROM = "from"
const val TO = "to"
const val NORTH = "north"
const val SOUTH = "south"
const val EAST = "east"
const val WEST = "west"
const val X = "x"
const val Y = "y"

class NodeDeserializer : JsonDeserializer<Node> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Node? {
        json ?: return null

        val jsonObject = json.asJsonObject
        val type = jsonObject.get(TYPE).asString
        val id = jsonObject.get(ID).asInt
        val name = jsonObject.get(NAME).asString
        val floor = jsonObject.get(FLOOR).asInt
        val building = when (jsonObject.get(BUILDING).asString) {
            ED -> Building.ED
            EC -> Building.EC
            else -> Building.UNKNOWN
        }

        val from = jsonObject.get(FROM)?.asInt
        val to = jsonObject.get(TO)?.asInt

        val neighbor = jsonObject.get(NEIGHBOR)?.asInt

        val up = jsonObject.get(UP)?.asInt
        val down = jsonObject.get(DOWN)?.asInt

        val north = jsonObject.get(NORTH)?.asInt
        val south = jsonObject.get(SOUTH)?.asInt
        val east = jsonObject.get(EAST)?.asInt
        val west = jsonObject.get(WEST)?.asInt

        val x = jsonObject.get(X)?.asFloat
        val y = jsonObject.get(Y)?.asFloat
        val coords = if (x != null && y != null) Pair(x, y) else null

        return when (type) {
            ENTRANCE -> Entrance(id, name, floor, building, from, to ?: return null)
            STAIRS -> Stairs(id, name, floor, building, neighbor ?: return null, up, down)
            ELEVATOR -> Elevator(id, name, floor, building, neighbor ?: return null, up, down)
            INTERSECTION -> Intersection(id, name, floor, building, north, south, east, west)
            LECTURE_HALL -> LectureHall(id, name, floor, building, neighbor ?: return null, coords ?: return null)
            LABORATORY -> Laboratory(id, name, floor, building, neighbor ?: return null, coords ?: return null)
            RESTROOM -> Restroom(id, name, floor, building, neighbor ?: return null, coords ?: return null)
            CLASSROOM -> Classroom(id, name, floor, building, neighbor ?: return null, coords ?: return null)
            OFFICE -> Office(id, name, floor, building, neighbor ?: return null, coords ?: return null)
            STORE -> Store(id, name, floor, building, neighbor ?: return null, coords ?: return null)
            else -> return UnknownRoom(id, name, floor, building, 0, coords ?: return null)
        }
    }

}