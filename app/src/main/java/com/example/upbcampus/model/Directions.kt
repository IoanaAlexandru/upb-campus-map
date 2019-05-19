package com.example.upbcampus.model

import com.example.upbcampus.R
import com.example.upbcampus.utils.*

abstract class Direction(
    val src: Node,
    val dst: Node
) {
    abstract fun getImage(): Int
}

private fun getStr(id: Int) = App.mResources?.getString(id)

class LocationDirection(loc: Node, private val info: Heading) :
    Direction(loc, loc) {
    override fun toString(): String {
        var message = ""

        message += if (info == Heading.UNKNOWN)
            "${getStr(R.string.start_from)} ${src.name}."
        else
            when (info) {
                Heading.FORWARD -> "${getStr(R.string.room)} ${getStr(R.string.front_side)}."
                Heading.RIGHT -> "${getStr(R.string.room)} ${getStr(R.string.is_on_side)} ${getStr(R.string.right_side)}."
                Heading.LEFT -> "${getStr(R.string.room)} ${getStr(R.string.is_on_side)} ${getStr(R.string.left_side)}."
                else -> "${getStr(R.string.reach_dest)}."
            }

        return message
    }

    override fun getImage(): Int =
        if (info == Heading.UNKNOWN) R.drawable.baseline_my_location_24 else R.drawable.baseline_account_balance_24
}

class EntranceDirection(private val loc: Node) : Direction(loc, loc) {
    override fun toString(): String {
        return "${getStr(R.string.enter_building)} ${loc.name}."
    }

    override fun getImage(): Int = R.drawable.ic_door
}

abstract class VerticalDirection(src: Node, dst: Node) :
    Direction(src, dst) {
    protected fun getElevation(): Int {
        return dst.floor - src.floor
    }

    override fun toString(): String {
        val x = getElevation()
        var message = ""

        message += if (x > 0) getStr(R.string.climb) else getStr(R.string.descend)

        message += "" + Math.abs(x) + " " + getStr(R.string.floors)
        message += " (${getStr(R.string.to_floor)} ${dst.floor})"

        return message
    }
}

class ElevatorDirection(src: Node, dst: Node) :
    VerticalDirection(src, dst) {

    // TODO toString
    override fun getImage(): Int = R.drawable.ic_elevator  // TODO maybe separate icons for up/down?
}

class StairsDirection(src: Node, dst: Node) :
    VerticalDirection(src, dst) {

    // TODO toString
    override fun getImage(): Int = if (getElevation() > 0) R.drawable.ic_stairs else R.drawable.ic_stairs_mirrored
}

class IntersectionDirection(src: Node, dst: Node, val info: Heading) :
    Direction(src, dst) {
    override fun toString(): String {
        return ""
    }

    override fun getImage(): Int = 0
}

class WalkDirection(src: Node, dst: Node) :
    Direction(src, dst) {
    override fun toString(): String {
        return ""  // TODO
    }

    override fun getImage(): Int = 0 // TODO
}