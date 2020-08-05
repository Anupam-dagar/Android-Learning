package com.example.unsplashapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RecyclerViewDataAdapter(private var imageData: MutableList<PexelsDataPhoto>, private var onImageClickListener: OnImageClickListener) :
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
        holder.imageName.text = imageData[position].photographer
        val sharedPrefs = holder.context.getSharedPreferences("imageapp", Context.MODE_PRIVATE)
        val currentLikes = sharedPrefs.getInt(imageData[position].id.toString(), 0).toString()
        holder.imageLikes.text = currentLikes
        holder.imageId.text = imageData[position].id.toString()
        Picasso.get()
            .load(imageData[position].src.large).into(holder.image)
    }

    class ViewHolder(view: View, onImageClickListener: OnImageClickListener) : RecyclerView.ViewHolder(view) {
        var image = view.findViewById<ImageView>(R.id.unsplashImageView)
        var imageName = view.findViewById<TextView>(R.id.imageName)
        var imageLikes = view.findViewById<TextView>(R.id.imageLikes)
        var onImageClickListener = onImageClickListener
        var likeButton = view.findViewById<Button>(R.id.likeButton)
        var imageId = view.findViewById<TextView>(R.id.photoId)
        val context = view.context

        init {
            itemView.setOnClickListener{
                onImageClickListener.onImageClick(adapterPosition)
            }
            likeButton.setOnClickListener{
                onImageClickListener.onLikeButtonClick(adapterPosition)
            }
        }
    }

    interface OnImageClickListener {
        fun onImageClick(position: Int)
        fun onLikeButtonClick(position: Int)
    }
}