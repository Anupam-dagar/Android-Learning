package com.example.unsplashapp

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RecyclerViewDataAdapter(private var onImageClickListener: OnImageClickListener) :
    RecyclerView.Adapter<RecyclerViewDataAdapter.ViewHolder>() {
    private var imageData = mutableListOf<PexelsDataPhoto>()
    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewDataAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.recycler_item, parent, false)
        sharedPrefs = parent.context.getSharedPreferences("imageapp", Context.MODE_PRIVATE)
        return ViewHolder(view, onImageClickListener)
    }

    override fun getItemCount(): Int = imageData.size


    override fun onBindViewHolder(holder: RecyclerViewDataAdapter.ViewHolder, position: Int) {
        holder.imageName.text = imageData[position].photographer
        val currentLikes = sharedPrefs.getInt(imageData[position].id.toString(), 0).toString()
        holder.imageLikes.text = currentLikes
        holder.imageId.text = imageData[position].id.toString()
        holder.imageId.visibility = View.GONE
        Picasso.get()
            .load(imageData[position].src.large).into(holder.image)
    }

    fun addImageData(newImages: Collection<PexelsDataPhoto>) {
        imageData.addAll(newImages)
        notifyDataSetChanged()
    }

    fun likeImage(currentLikes: Int, photoId: String, position: Int) {
        val editor = sharedPrefs.edit()
        editor.putInt(photoId, currentLikes + 1)
        editor.apply()
        editor.commit()
        notifyItemChanged(position)
    }

    inner class ViewHolder(view: View, onImageClickListener: OnImageClickListener) :
        RecyclerView.ViewHolder(view) {
        var image = view.findViewById<ImageView>(R.id.unsplashImageView)
        var imageName = view.findViewById<TextView>(R.id.imageName)
        var imageLikes = view.findViewById<TextView>(R.id.imageLikes)
        var onImageClickListener = onImageClickListener
        var likeButton = view.findViewById<Button>(R.id.likeButton)
        var imageId = view.findViewById<TextView>(R.id.photoId)

        init {
            itemView.setOnClickListener {
                onImageClickListener.onImageClick(imageData[adapterPosition], imageLikes.text.toString())
            }
            likeButton.setOnClickListener {
                val currentLikes = sharedPrefs.getInt(imageData[adapterPosition].id.toString(), 0)
                onImageClickListener.onLikeButtonClick(imageData[adapterPosition], currentLikes, imageId.text.toString(), adapterPosition)
            }
        }
    }

    interface OnImageClickListener {
        fun onImageClick(imageData: PexelsDataPhoto, likes: String)
        fun onLikeButtonClick(imageData: PexelsDataPhoto, currentLikes: Int, photoId: String, position: Int)
    }
}