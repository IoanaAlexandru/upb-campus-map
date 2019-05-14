package com.example.upbcampus

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import uk.co.senab.photoview.PhotoViewAttacher

class EdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ed)

        val buttonFloor1 = findViewById(R.id.ed_floor1) as? Button
        val buttonFloor2 = findViewById(R.id.ed_floor2) as? Button
        val buttonFloor3 = findViewById(R.id.ed_floor3) as? Button
        val buttonFloor4 = findViewById(R.id.ed_floor4) as? Button
        val image = findViewById(R.id.edImage) as? ImageView
        image?.setImageResource(R.drawable.ed_parter)

        val photoView = PhotoViewAttacher(image)
        photoView.update()

        buttonFloor1?.setOnClickListener {
            println("You clicked on floor1")
            image?.setImageResource(R.drawable.ed_parter)
            setButtonStyleBlack(buttonFloor1)
            setButtonStyleGray(buttonFloor2)
            setButtonStyleGray(buttonFloor3)
            setButtonStyleGray(buttonFloor4)
        }

        buttonFloor2?.setOnClickListener {
            println("You clicked on floor2")
            image?.setImageResource(R.drawable.ed_etaj)
            setButtonStyleBlack(buttonFloor2)
            setButtonStyleGray(buttonFloor1)
            setButtonStyleGray(buttonFloor3)
            setButtonStyleGray(buttonFloor4)
        }

        buttonFloor3?.setOnClickListener {
            println("You clicked on floor2")
            image?.setImageResource(R.drawable.ed_etaj2)
            setButtonStyleBlack(buttonFloor3)
            setButtonStyleGray(buttonFloor1)
            setButtonStyleGray(buttonFloor2)
            setButtonStyleGray(buttonFloor4)
        }

        buttonFloor4?.setOnClickListener {
            println("You clicked on floor2")
            image?.setImageResource(R.drawable.ed)
            setButtonStyleBlack(buttonFloor4)
            setButtonStyleGray(buttonFloor2)
            setButtonStyleGray(buttonFloor3)
            setButtonStyleGray(buttonFloor1)
        }
    }

    fun setButtonStyleGray(button: Button?) {
        button?.setBackgroundColor(resources.getColor(R.color.colorWhite))
        button?.setTextColor(resources.getColor(R.color.colorDarkGray))
    }

    fun setButtonStyleBlack(button: Button?) {
        button?.setBackgroundColor(resources.getColor(R.color.colorGray))
        button?.setTextColor(resources.getColor(R.color.colorBlack))
    }
}
