package com.example.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


class DetailFragment : Fragment() {
    fun DetailFragment(){

    }
    lateinit var heading : TextView;
    lateinit var body : TextView;
    var headingText: String = ""
    var bodyText: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.detail_fragment, container, false)
        heading = view.findViewById(R.id.heading)
        body = view.findViewById(R.id.body)
        heading.text = headingText
        body.text = bodyText
        return view
    }

    fun change(newHeading: String?, newBody: String?) {
        heading.text = newHeading
        body.text = newBody
    }


}