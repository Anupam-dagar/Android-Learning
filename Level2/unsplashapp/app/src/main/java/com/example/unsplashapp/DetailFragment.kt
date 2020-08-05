package com.example.unsplashapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.detail_fragment, container, false)
        val image = view.findViewById<ImageView>(R.id.displayImageView)
        val imageName = view.findViewById<TextView>(R.id.imageNameTextView)
        val imageLikes = view.findViewById<TextView>(R.id.likeCountTextView)

        image.setImageResource(R.drawable.city)
        imageName.text = "testing image name"
        imageLikes.text = "25"
        return view
    }
}