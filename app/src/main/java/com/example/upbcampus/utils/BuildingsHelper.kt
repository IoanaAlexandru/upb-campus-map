package com.example.upbcampus.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import com.example.upbcampus.R
import com.example.upbcampus.mapmodel.Room
import com.example.upbcampus.mapmodel.UPBMap

var FLOORS = 0
var CURRENT_FLOOR = 0
var CURRENT_BUILDING = "EC"

var XTHRESHOLD = 0.03
var YTHRESHOLD = 0.04

fun getRoomInfo(
    x: Float,
    y: Float,
    currentFloor: Int,
    currentBuilding: String,
    xThreshold: Double,
    yThreshold: Double
): Room? {
    UPBMap.roomsByLocation.forEach { (location, nodes) ->
        // Get the entries for current floor and building
        if (location.first == currentFloor && location.second.name == currentBuilding) {
            nodes.forEach { node ->
                // Check left | right | up | down borders for current room
                if ((x >= node.coords.first - xThreshold) &&
                    (x <= node.coords.first + xThreshold) &&
                    (y >= node.coords.second - yThreshold) &&
                    (y <= node.coords.second + yThreshold)
                ) {
                    return node
                }
            }
        }
    }
    return null
}

fun changeButtonsImages(
    activity: Activity?,
    ctx: Context?,
    image: ImageView?,
    rootView: View?
) {
    for (i in 0..FLOORS) {
        var building = "ec_floor"
        if (CURRENT_BUILDING == "ED") building = "ed_floor"

        val current_button = rootView?.findViewById(
            ctx?.resources!!.getIdentifier("$building$i", "id", activity?.packageName)
        ) as? Button

        current_button?.setOnClickListener {
            when (i) {
                0 -> {
                    if (CURRENT_BUILDING == "EC")
                        image?.setImageResource(R.drawable.ec_parter)
                    else
                        image?.setImageResource(R.drawable.ed_parter)
                }
                1 -> {
                    if (CURRENT_BUILDING == "EC")
                        image?.setImageResource(R.drawable.ec_etaj)
                    else
                        image?.setImageResource(R.drawable.ed_etaj1)
                }
                2 -> {
                    if (CURRENT_BUILDING == "ED")
                        image?.setImageResource(R.drawable.ed_etaj2)
                }
                3 -> {
                    if (CURRENT_BUILDING == "ED")
                        image?.setImageResource(R.drawable.ed_etaj3)
                }
                4 -> {
                    if (CURRENT_BUILDING == "ED")
                        image?.setImageResource(R.drawable.ed_etaj4)
                }
            }

            setButtonStyleBlack(ctx, current_button)
            CURRENT_FLOOR = i

            for (j in 0..FLOORS) {
                if (j != i) {
                    val other_button = rootView.findViewById(
                        ctx?.resources!!.getIdentifier("$building$j", "id", activity?.packageName)
                    ) as? Button
                    setButtonStyleGray(ctx, other_button)
                }
            }
        }
    }
}

fun DisplayPopUp(ctx: Context?, layoutInflater: LayoutInflater?, x: Float, y: Float) {
    Log.d("BuildingsHelper", "New click at $x $y")
    val room = getRoomInfo(x, y, CURRENT_FLOOR, CURRENT_BUILDING, XTHRESHOLD, YTHRESHOLD)

    if (room != null) {
        val alertDialog = AlertDialog.Builder(ctx)
        val view = layoutInflater?.inflate(R.layout.custompopup, null)
        val list = view?.findViewById(R.id.list_room) as? ListView

        val info = arrayOf(
            "Name: ${room.name}",
            "Building: ${room.building}",
            "Floor: ${room.floor}",
            "Type: ${room::class.java.simpleName}"
        )
        val listAdapter = ArrayAdapter<String>(ctx, android.R.layout.simple_list_item_1, info)

        list?.adapter = listAdapter
        listAdapter.notifyDataSetChanged()

        val dialog = alertDialog.setView(view)
        dialog.create()
            .show()
    }
}

fun setButtonStyleGray(ctx: Context?, button: Button?) {
    button?.setBackgroundColor(ctx?.resources!!.getColor(R.color.colorWhite))
    button?.setTextColor(ctx?.resources!!.getColor(R.color.colorDarkGray))
}

fun setButtonStyleBlack(ctx: Context?, button: Button?) {
    button?.setBackgroundColor(ctx?.resources!!.getColor(R.color.colorGray))
    button?.setTextColor(ctx?.resources!!.getColor(R.color.colorBlack))
}

