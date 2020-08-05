package com.example.unsplashapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewDataAdapter(private var imageData: MutableList<String>) :
    RecyclerView.Adapter<RecyclerViewDataAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewDataAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = imageData.size


    override fun onBindViewHolder(holder: RecyclerViewDataAdapter.ViewHolder, position: Int) {
        holder.imageName.text = "image name"
        holder.imageLikes.text = "20"
        holder.image.setImageResource(R.drawable.city)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image = view.findViewById<ImageView>(R.id.unsplashImageView)
        var imageName = view.findViewById<TextView>(R.id.imageName)
        var imageLikes = view.findViewById<TextView>(R.id.imageLikes)
    }

}