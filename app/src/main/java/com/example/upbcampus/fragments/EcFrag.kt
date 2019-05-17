package com.example.upbcampus.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import com.example.upbcampus.R
import com.example.upbcampus.utils.*
import uk.co.senab.photoview.PhotoViewAttacher
import uk.co.senab.photoview.PhotoViewAttacher.OnPhotoTapListener

class EcFrag : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_ec, container, false)
        val image = rootView.findViewById(R.id.ecImage) as? ImageView

        val photoView = PhotoViewAttacher(image)
        photoView.update()

        FLOORS = 1
        CURRENT_BUILDING = "EC"

        changeButtonsImages(activity, context, image, rootView)

        photoView.onPhotoTapListener = object : OnPhotoTapListener {
            override fun onOutsidePhotoTap() {}

            @SuppressLint("InflateParams")
            override fun onPhotoTap(arg0: View, x: Float, y: Float) {
                DisplayPopUp(context, layoutInflater, x, y)
            }
        }

        return rootView
    }
}