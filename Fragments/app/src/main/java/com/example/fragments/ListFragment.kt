package com.example.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment


class ListFragment : ListFragment() {
    val usersList =
        arrayOf("GitHub Trip", "Berlin Tour", "Travelling Destination", "Tech Conferences")
    val location =
        arrayOf("Recently, I got a chance to attend GitHub Satellite, the conference which brings big ideas and memorable content to cities around the world.", "From the latest GitHub product news to hands-on workshops, GitHub Satellite brings together thousands of developers to explore what’s next.", "t was a phenomenal experience attending the conference. I attended GitHub Satellite as a GitHub Campus Expert, India along with other 10 from different parts of the world.", "I reached Berlin well in advance of the event and had plenty of time to explore the city. So on the first day, I took the Berlin tour and got to know about the stories related to all the historical buildings there.")
    var isTabletMode = false;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_fragment, container, false)
        isTabletMode = inflater.context.resources.configuration.smallestScreenWidthDp >= 600 && inflater.context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        val adapter = ArrayAdapter<String>(inflater.context,android.R.layout.simple_list_item_1, usersList)
        setListAdapter(adapter)
        return view
    }

    override fun onListItemClick(listview: ListView, view: View, position: Int, id: Long){
        if (isTabletMode){
            val detailFragment = fragmentManager?.findFragmentById(R.id.detailfragment) as DetailFragment
            detailFragment.change(usersList[position], location[position])
        }
        else {
            val detailFragment = DetailFragment()
            detailFragment.headingText = usersList[position]
            detailFragment.bodyText = location[position]

            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_holder, detailFragment)
            transaction?.addToBackStack(null)
            transaction?.commit()
        }

    }
}