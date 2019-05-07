package com.example.upbcampus

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class EcActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ec)

        val buttonFloor1 = findViewById(R.id.ec_floor1) as? Button
        val buttonFloor2 = findViewById(R.id.ec_floor2) as? Button
        val image = findViewById(R.id.ecImage) as? ImageView

        buttonFloor1?.setOnClickListener {
                println("You clicked on floor1")
                image?.setImageResource(R.drawable.ec_parter)
        }

        buttonFloor2?.setOnClickListener {
                println("You clicked on floor2")
                image?.setImageResource(R.drawable.ec_etaj)
        }
    }
}
