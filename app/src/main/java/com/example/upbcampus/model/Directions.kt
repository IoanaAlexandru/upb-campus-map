package com.example.upbcampus.model

import com.example.upbcampus.R
import com.example.upbcampus.utils.*

abstract class Direction(
    val src: Node,
    val dst: Node
)

private fun getStr(id: Int) = App.mResources?.getString(id)

class LocationDirection(loc: Node, private val info: Heading) :
    Direction(loc, loc)
{
    override fun toString(): String {
        var message = ""

        message += if (info == Heading.UNKNOWN)
            "${getStr(R.string.start_from)} ${src.name}."
        else
            when(info) {
                Heading.FORWARD -> "${getStr(R.string.room)} ${getStr(R.string.front_side)}."
                Heading.RIGHT -> "${getStr(R.string.room)} ${getStr(R.string.is_on_side)} ${getStr(R.string.right_side)}."
                Heading.LEFT -> "${getStr(R.string.room)} ${getStr(R.string.is_on_side)} ${getStr(R.string.left_side)}."
                else -> "${getStr(R.string.reach_dest)}."
            }

        return message
    }
}

class EntranceDirection(private val loc: Node)
    : Direction (loc, loc) {
    override fun toString(): String {
        return "${getStr(R.string.enter_building)} ${loc.name}."
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

        message += if (x > 0) getStr(R.string.climb) else getStr(R.string.descend)

        message += "" + Math.abs(x) + " " + getStr(R.string.floors)
        message += " (${getStr(R.string.to_floor)} ${dst.floor})"

        return message
    }
}

class IntersectionDirection(src: Node, dst: Node, val info: Heading) :
    Direction(src, dst)
{
    override fun toString() : String {
        return ""
    }
}

class WalkDirection(src: Node, dst: Node) :
    Direction(src, dst)