package com.example.upbcampus

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class EdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ed)

        val buttonFloor1 = findViewById(R.id.ed_floor1) as? Button
        val buttonFloor2 = findViewById(R.id.ed_floor2) as? Button
        val buttonFloor3 = findViewById(R.id.ed_floor3) as? Button
        val buttonFloor4 = findViewById(R.id.ed_floor4) as? Button
        val image = findViewById(R.id.edImage) as? ImageView

        buttonFloor1?.setOnClickListener {
            println("You clicked on floor1")
            image?.setImageResource(R.drawable.ed_parter)
        }

        buttonFloor2?.setOnClickListener {
            println("You clicked on floor2")
            image?.setImageResource(R.drawable.ed_etaj)
        }

        buttonFloor3?.setOnClickListener {
            println("You clicked on floor2")
            image?.setImageResource(R.drawable.ed_etaj2)
        }

        buttonFloor4?.setOnClickListener {
            println("You clicked on floor2")
            image?.setImageResource(R.drawable.ed)
        }
    }
}
