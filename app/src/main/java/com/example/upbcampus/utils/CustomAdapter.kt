package com.example.upbcampus.utils

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.upbcampus.R
import com.example.upbcampus.fragments.EcFrag
import com.example.upbcampus.fragments.EdFrag

import java.util.ArrayList

class DataModel(val name: String, val id: Int, val image: Int)

/**
 * This is a CustomAdapter used to display the buildings.
 * One model has an ImageView and a TextView.
 */
class CustomAdapter(private val dataSet: ArrayList<DataModel>, internal var context: Context) :
    RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {

    val FRAG_EC = "ec_frag"
    val FRAG_ED = "ed_frag"

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var textViewName: TextView
        internal var imageViewIcon: ImageView

        init {
            this.textViewName = itemView.findViewById(R.id.textViewName)
            this.imageViewIcon = itemView.findViewById(R.id.imageView)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cards_layout, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, listPosition: Int) {

        val textViewName = holder.textViewName
        val imageView = holder.imageViewIcon

        textViewName.text = dataSet[listPosition].name
        imageView.setImageResource(dataSet[listPosition].image)

        imageView.setOnClickListener {
            Log.d("CustomAdapter","You clicked on image.Redirecting...")
            Log.d("CustomAdapter", dataSet[listPosition].name)
            when (dataSet[listPosition].name) {
                "EC" -> {
                    loadFragByTag(FRAG_EC)
                }

                "ED" -> {
                    loadFragByTag(FRAG_ED)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    private fun loadFragByTag(tag: String) {
        val frag = getFragByTag(tag)

        (context as AppCompatActivity).supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_main, frag, tag)
            .commit()
    }

    private fun getFragByTag(tag: String): Fragment {
        var frag = (context as AppCompatActivity).supportFragmentManager.findFragmentByTag(tag)

        if (frag == null) {
            Log.d("CustomAdapter", "$tag not found, creating a new one.")
            frag = when (tag) {
                FRAG_EC -> EcFrag()
                FRAG_ED -> EdFrag()
                else -> Fragment()  // shouldn't happen
            }

            (context as AppCompatActivity).supportFragmentManager
                .beginTransaction()
                .add(frag, tag)
                .commit()
        } else {
            Log.d("CustomAdapter", "$tag found.")
        }

        return frag
    }
}
