package com.example.upbcampus.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import com.example.upbcampus.R
import com.example.upbcampus.mapmodel.Building
import com.example.upbcampus.mapmodel.Room
import com.example.upbcampus.mapmodel.UPBMap
import uk.co.senab.photoview.PhotoViewAttacher
import uk.co.senab.photoview.PhotoViewAttacher.OnPhotoTapListener


class EcFrag : Fragment() {

    var xThreshold = 0.03
    var yThreshold = 0.04

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_ec, container, false)

        val buttonFloor1 = rootView.findViewById(R.id.ec_floor1) as? Button
        val buttonFloor2 = rootView.findViewById(R.id.ec_floor2) as? Button
        val image = rootView.findViewById(R.id.ecImage) as? ImageView

        val photoView = PhotoViewAttacher(image)
        photoView.update()

        photoView.onPhotoTapListener = object : OnPhotoTapListener {
            override fun onOutsidePhotoTap() {}

            @SuppressLint("InflateParams")
            override fun onPhotoTap(arg0: View, x: Float, y: Float) {
                println("New click...\n".plus(x).plus(" ").plus(y))

                UPBMap.roomsByLocation.forEach { (location, nodes) ->
                    print("${location.first}, ${location.second} : ")
                    nodes.forEach { node ->
                        print("${node.name} ")
                    }
                    println()
                }
                val room = getRoomInfo(x, y)

                if (room != null) {
                    val altertDialog = AlertDialog.Builder(context)
                    val view = layoutInflater.inflate(R.layout.custompopup, null)
                    val list = view.findViewById(R.id.list_room) as? ListView

                    val info = arrayOf("Name: ".plus(room.name), "Building: ".plus(room.building), "Floor: ".plus(room.floor), "Type: ".plus(room::class.java.simpleName))
                    val listAdapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, info)

                    list?.adapter = listAdapter
                    listAdapter.notifyDataSetChanged()

                    val diagol = altertDialog.setView(view)
                    diagol.create()
                        .show()
                }
            }
        }

        buttonFloor1?.setOnClickListener {
            Log.d("EcFrag", "You clicked on floor1")
            image?.setImageResource(R.drawable.ec_parter)
            setButtonStyleBlack(buttonFloor1)
            setButtonStyleGray(buttonFloor2)
        }

        buttonFloor2?.setOnClickListener {
            Log.d("EcFrag", "You clicked on floor2")
            image?.setImageResource(R.drawable.ec_etaj)
            setButtonStyleBlack(buttonFloor2)
            setButtonStyleGray(buttonFloor1)
        }

        return rootView
    }

    private fun setButtonStyleGray(button: Button?) {
        button?.setBackgroundColor(resources.getColor(R.color.colorWhite))
        button?.setTextColor(resources.getColor(R.color.colorDarkGray))
    }

    private fun setButtonStyleBlack(button: Button?) {
        button?.setBackgroundColor(resources.getColor(R.color.colorGray))
        button?.setTextColor(resources.getColor(R.color.colorBlack))
    }

    private fun getRoomInfo(x: Float, y: Float): Room? {

        UPBMap.roomsByLocation.forEach { (_, nodes) ->
            nodes.forEach { node ->
                println(node.name.plus(" ").plus(node.coords.first).plus(" ").plus(node.coords.second))
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
        return null
    }
}

