package com.example.upbcampus.utils

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.example.upbcampus.R
import com.example.upbcampus.model.Room
import com.example.upbcampus.model.UPBMap

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

fun displayPopUp(ctx: Context?, layoutInflater: LayoutInflater?, x: Float, y: Float) {
    Log.d("BuildingsHelper", "New click at $x $y")
    val room = getRoomInfo(x, y, CURRENT_FLOOR, CURRENT_BUILDING, XTHRESHOLD, YTHRESHOLD)

    App.mActivity?.displayRoomInfo(room, ctx, layoutInflater)
}

fun PopupInfoByFrag() {

}

fun AddToFavouriteList(name: String, fav_list: MutableList<String>) {
    fav_list.add(name)
}

fun setButtonStyleGray(ctx: Context?, button: Button?) {
    button?.setBackgroundColor(ctx?.resources!!.getColor(R.color.colorWhite))
    button?.setTextColor(ctx?.resources!!.getColor(R.color.colorDarkGray))
}

fun setButtonStyleBlack(ctx: Context?, button: Button?) {
    button?.setBackgroundColor(ctx?.resources!!.getColor(R.color.colorGray))
    button?.setTextColor(ctx?.resources!!.getColor(R.color.colorBlack))
}

