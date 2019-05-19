package com.example.upbcampus.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView

import com.example.upbcampus.R
import com.example.upbcampus.model.Direction
import com.example.upbcampus.model.UPBMap
import com.example.upbcampus.model.UPBUser
import com.example.upbcampus.utils.*
import java.util.ArrayList

class NavigateFrag : Fragment() {

    private val directions = ArrayList<Direction>()
    private val image = ArrayList<Int>()
    private val id = ArrayList<Int>()
    private lateinit var data: ArrayList<NavDataModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_navigate, container, false)
        val listView = rootView.findViewById(R.id.nav_lv) as? ListView
        listView?.transcriptMode = ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL
        listView?.isStackFromBottom = true

        data = ArrayList()

        /* Complete ListView with direction texts and its images */
        UPBMap.navigate(UPBUser.src, UPBUser.dst ?: return rootView).forEach { directions.add(it.toString()) }

        for (i in 0 until directions.size) {
            image.add(directions[i].getImage())
            id.add(i)
        }

        for (i in 0 until id.size) {
            data.add(
                NavDataModel(
                    directions[i].toString(),
                    id[i],
                    image[i]
                )
            )
        }

        val customAdapter = NavigateAdapter(data, context!!)
        listView!!.adapter = customAdapter

        return rootView
    }

    // TODO Each Direction should have a getImage() method
    private fun getDirectionImage(direction: String): Int {
//        when (direction) {
//            descend -> {
//                return R.drawable.ic_stairs_mirrored            }
//            climb -> {
//                return R.drawable.ic_stairs
//            }
//            floors -> {
//                return R.drawable.baseline_unfold_more_24
//            }
//            to_floor -> {
//                return R.drawable.baseline_unfold_more_24
//            }
//            start_from -> {
//                return R.drawable.baseline_my_location_24
//            }
//            room -> {
//                return R.drawable.baseline_account_balance_24
//            }
//            is_on_side -> {
//                return R.drawable.baseline_account_balance_24
//            }
//            left_side -> {
//                return R.drawable.baseline_arrow_back_24
//            }
//            right_side -> {
//                return R.drawable.baseline_arrow_forward_24
//            }
//            front_side -> {
//                return R.drawable.baseline_arrow_upward_24
//            }
//            reach_dest -> {
//                return R.drawable.baseline_pin_drop_24
//            }
//            enter_building -> {
//                return R.drawable.ic_door
//            }
//            go_right -> {
//                return R.drawable.baseline_arrow_forward_24
//            }
//            go_left -> {
//                return R.drawable.baseline_arrow_back_24
//            }
//            go_forward -> {
//                return R.drawable.baseline_arrow_upward_24
//            }
//            go_back -> {
//                return R.drawable.baseline_arrow_downward_24
//            }
//            towards -> {
//                return R.drawable.baseline_arrow_downward_24
//            }
//        }
        return 0
    }
}
