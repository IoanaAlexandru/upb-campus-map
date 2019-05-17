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


class EdFrag : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_ed, container, false)
        val buttonFloor1 = rootView.findViewById(R.id.ed_floor0) as? Button
        val buttonFloor2 = rootView.findViewById(R.id.ed_floor1) as? Button
        val buttonFloor3 = rootView.findViewById(R.id.ed_floor2) as? Button
        val buttonFloor4 = rootView.findViewById(R.id.ed_floor3) as? Button
        val buttonFloor5 = rootView.findViewById(R.id.ed_floor4) as? Button
        val image = rootView.findViewById(R.id.edImage) as? ImageView
        image?.setImageResource(R.drawable.ed_parter)

        val photoView = PhotoViewAttacher(image)
        photoView.update()

        buttonFloor1?.setOnClickListener {
            image?.setImageResource(R.drawable.ed_parter)
            setButtonStyleBlack(buttonFloor1)
            setButtonStyleGray(buttonFloor2)
            setButtonStyleGray(buttonFloor3)
            setButtonStyleGray(buttonFloor5)
            setButtonStyleGray(buttonFloor4)
        }

        buttonFloor2?.setOnClickListener {
            image?.setImageResource(R.drawable.ed_etaj1)
            setButtonStyleBlack(buttonFloor2)
            setButtonStyleGray(buttonFloor1)
            setButtonStyleGray(buttonFloor5)
            setButtonStyleGray(buttonFloor3)
            setButtonStyleGray(buttonFloor4)
        }

        buttonFloor3?.setOnClickListener {
            image?.setImageResource(R.drawable.ed_etaj2)
            setButtonStyleBlack(buttonFloor3)
            setButtonStyleGray(buttonFloor1)
            setButtonStyleGray(buttonFloor5)
            setButtonStyleGray(buttonFloor2)
            setButtonStyleGray(buttonFloor4)
        }

        buttonFloor4?.setOnClickListener {
            image?.setImageResource(R.drawable.ed_etaj3)
            setButtonStyleBlack(buttonFloor4)
            setButtonStyleGray(buttonFloor2)
            setButtonStyleGray(buttonFloor5)
            setButtonStyleGray(buttonFloor3)
            setButtonStyleGray(buttonFloor1)
        }

        buttonFloor5?.setOnClickListener {
            image?.setImageResource(R.drawable.ed_etaj4)
            setButtonStyleBlack(buttonFloor5)
            setButtonStyleGray(buttonFloor2)
            setButtonStyleGray(buttonFloor4)
            setButtonStyleGray(buttonFloor3)
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
