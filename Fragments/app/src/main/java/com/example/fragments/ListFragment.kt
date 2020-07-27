package com.example.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment


class ListFragment : ListFragment() {
    val usersList =
        arrayOf("Item 1", "Item 2", "Item 3", "Item 4")
    val location =
        arrayOf("Body 1", "Body 2", "Body 3", "Body 4")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_fragment, container, false)
        val adapter = ArrayAdapter<String>(inflater.context,android.R.layout.simple_list_item_1, usersList)
        setListAdapter(adapter)
        return view
    }

    override fun onListItemClick(listview: ListView, view: View, position: Int, id: Long){
        val detailFragment = DetailFragment()
        detailFragment.headingText = "Name: ${usersList[position]}"
        detailFragment.bodyText = "Location: ${location[position]}"

        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragment_holder, detailFragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }
}