package com.example.unsplashapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewDataAdapter(private var imageData: MutableList<String>, private var onImageClickListener: OnImageClickListener) :
    RecyclerView.Adapter<RecyclerViewDataAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewDataAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(view, onImageClickListener)
    }

    override fun getItemCount(): Int = imageData.size


    override fun onBindViewHolder(holder: RecyclerViewDataAdapter.ViewHolder, position: Int) {
        holder.imageName.text = "image name"
        holder.imageLikes.text = "20"
        holder.image.setImageResource(R.drawable.city)
    }

    class ViewHolder(view: View, onImageClickListener: OnImageClickListener) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var image = view.findViewById<ImageView>(R.id.unsplashImageView)
        var imageName = view.findViewById<TextView>(R.id.imageName)
        var imageLikes = view.findViewById<TextView>(R.id.imageLikes)
        var onImageClickListener = onImageClickListener

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onImageClickListener.onImageClick(adapterPosition)
        }
    }

    interface OnImageClickListener {
        fun onImageClick(position: Int)
    }

}