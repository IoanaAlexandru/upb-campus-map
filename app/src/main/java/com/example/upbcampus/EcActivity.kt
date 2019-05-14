package com.example.upbcampus

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.support.v4.view.ViewCompat.animate
import android.R.attr.x
import android.R.attr.y
import android.R.attr.start
import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.support.v4.view.ViewCompat.setY
import android.support.v4.view.ViewCompat.setX
import android.R.attr.y
import android.R.attr.x







class EcActivity : AppCompatActivity() {


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ec)

        val buttonFloor1 = findViewById(R.id.ec_floor1) as? Button
        val buttonFloor2 = findViewById(R.id.ec_floor2) as? Button
        val zoomIn = findViewById(R.id.zoom_in) as? Button
        val zoomOut = findViewById(R.id.zoom_out) as? Button
        val image = findViewById(R.id.ecImage) as? ImageView

        var dx = 0
        var dy = 0

        image?.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.getAction()) {
                    MotionEvent.ACTION_DOWN -> {
                        val x = event.getX()
                        val y = event.getY()
                        dx = (x - image.getX()).toInt()
                        dy = (y - image.getY()).toInt()
                    }
                    MotionEvent.ACTION_MOVE -> {
                        image.setX(event.getX() - dx)
                        image.setY(event.getY() - dy)
                    }
                }
                return true
            }
        })

        buttonFloor1?.setOnClickListener {
                println("You clicked on floor1")
                //image?.setImageResource(R.drawable.ec_parter)
                image?.setBackgroundResource(R.drawable.ec_parter)
                buttonFloor1.setBackgroundColor(resources.getColor(R.color.colorGray))
                buttonFloor1.setTextColor(resources.getColor(R.color.colorBlack))
                buttonFloor2?.setBackgroundColor(resources.getColor(R.color.colorWhite))
                buttonFloor2?.setTextColor(resources.getColor(R.color.colorDarkGray))
        }

        buttonFloor2?.setOnClickListener {
                println("You clicked on floor2")
                image?.setBackgroundResource(R.drawable.ec_etaj)
                buttonFloor2.setBackgroundColor(resources.getColor(R.color.colorGray))
                buttonFloor2.setTextColor(resources.getColor(R.color.colorBlack))
                buttonFloor1?.setBackgroundColor(resources.getColor(R.color.colorWhite))
                buttonFloor1?.setTextColor(resources.getColor(R.color.colorDarkGray))
        }

        zoomIn?.setOnClickListener {
            println("Zooming in...")
            val x = image?.getScaleX()
            val y = image?.getScaleY()

            if (x != null)
                image?.setScaleX(x + 1)
            if (y != null)
                image?.setScaleY(y + 1)
        }

        zoomOut?.setOnClickListener {
            println("Zooming out...")
            val x = image?.getScaleX()
            val y = image?.getScaleY()

            if (x != null)
                image?.setScaleX(x - 1)
            if (y != null)
                image?.setScaleY(y - 1)
        }
    }
}
