package com.example.upbcampus.model

import com.example.upbcampus.utils.*

abstract class Direction(
    val src: Node,
    val dst: Node
)

class LocationDirection(private val loc: Node, private val info: Heading) :
    Direction(loc, loc)
{
    override fun toString(): String {
        var message = ""

        message += if (info == Heading.UNKNOWN)
            "$start_from " + src.name + "."
        else
            when(info) {
                Heading.FORWARD -> "$room ${loc.name} $front_side."
                Heading.RIGHT -> "$room ${loc.name} $is_on_side $right_side."
                Heading.LEFT -> "$room ${loc.name} $is_on_side $left_side."
                else -> "$reach_dest."
            }

        return message
    }
}

class EntranceDirection(private val loc: Node)
    : Direction (loc, loc) {
    override fun toString(): String {
        return enter_building + " " + loc.name + "."
    }
}

class VerticalDirection(src: Node, dst: Node) :
    Direction(src, dst)
{
    private fun getElevation() : Int {
        return dst.floor - src.floor
    }

    override fun toString() : String {
        val x = getElevation()
        var message = ""

        message += if (x > 0) climb else descend

        message += "" + Math.abs(x) + " " + floors
        message += " ($to_floor " + dst.floor + ")"

        return message
    }
}

class IntersectionDirection(src: Node, dst: Node, val info: Heading) :
    Direction(src, dst)
{
    override fun toString() : String {
        return when(info) {
            Heading.FORWARD -> "$go_forward."
            Heading.LEFT -> "$go_left."
            Heading.RIGHT -> "$go_right."
            Heading.BACK -> "$go_back."
            else -> "Taxi du-mă unde vrei."
        }
    }
}

class WalkDirection(src: Node, dst: Node) :
    Direction(src, dst) {
    override fun toString(): String {
        return "$go_forward."
    }
}