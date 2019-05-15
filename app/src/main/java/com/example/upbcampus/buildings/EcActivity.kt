package com.example.upbcampus.buildings

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.upbcampus.R
import uk.co.senab.photoview.PhotoViewAttacher


class EcActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ec)

        val buttonFloor1 = findViewById(R.id.ec_floor1) as? Button
        val buttonFloor2 = findViewById(R.id.ec_floor2) as? Button
        val image = findViewById(R.id.ecImage) as? ImageView
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
