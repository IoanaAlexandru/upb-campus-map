package com.example.upbcampus.utils

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.upbcampus.R

import java.util.ArrayList

class NavDataModel(val name: String, val id: Int, val image: Int) {

}

/**
 * This is a CustomAdapter used to display the buildings.
 * One model has an ImageView and a TextView.
 */
class NavigateAdapter(private val dataSet: ArrayList<NavDataModel>, internal var context: Context) :
    BaseAdapter() {

    override fun getItem(p0: Int): Any {
        return dataSet[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return dataSet.size
    }

    class ViewHolder {
        var direction_text: TextView? = null
        internal var direction_img: ImageView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder

        if (convertView == null) {
            holder = ViewHolder()
            val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.navigate, null, true)

            holder.direction_text = convertView!!.findViewById(R.id.direction_text) as TextView
            holder.direction_img = convertView.findViewById(R.id.direction_image) as ImageView

            convertView.tag = holder
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = convertView.tag as ViewHolder
        }

        holder.direction_text!!.setText(dataSet[position].name)
        holder.direction_img!!.setImageResource(dataSet[position].image)

        return convertView
    }
}