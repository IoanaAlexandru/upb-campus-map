package com.example.upbcampus.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.example.upbcampus.R
import com.example.upbcampus.utils.*
import uk.co.senab.photoview.PhotoViewAttacher


class EdFrag : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_ed, container, false)
        val image = rootView.findViewById(R.id.edImage) as? ImageView
        image?.setImageResource(R.drawable.ed_parter)

        val photoView = PhotoViewAttacher(image)
        photoView.update()

        FLOORS = 4
        CURRENT_BUILDING = "ED"

        changeButtonsImages(activity, context, image, rootView)

        return rootView
    }
}
