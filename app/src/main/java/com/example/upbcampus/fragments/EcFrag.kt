package com.example.upbcampus.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView

import com.example.upbcampus.R
import uk.co.senab.photoview.PhotoViewAttacher

class EcFrag : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_ec, container, false)

        val buttonFloor1 = rootView.findViewById(R.id.ec_floor1) as? Button
        val buttonFloor2 = rootView.findViewById(R.id.ec_floor2) as? Button
        val image = rootView.findViewById(R.id.ecImage) as? ImageView
        image?.setImageResource(R.drawable.ec_parter)

        val photoView = PhotoViewAttacher(image)
        photoView.update()

        buttonFloor1?.setOnClickListener {
            println("You clicked on floor1")
            image?.setImageResource(R.drawable.ec_parter)
            setButtonStyleBlack(buttonFloor1)
            setButtonStyleGray(buttonFloor2)
        }

        buttonFloor2?.setOnClickListener {
            println("You clicked on floor2")
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
}
