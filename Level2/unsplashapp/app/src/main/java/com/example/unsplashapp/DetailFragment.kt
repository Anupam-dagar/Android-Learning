package com.example.unsplashapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {

    companion object {
        fun newInstance(photographer: String, likes: String, photoId: Int, photoUrl: String): DetailFragment {
            val args = Bundle()
            args.putString("photographer", photographer)
            args.putString("likes", likes)
            args.putString("photoId", photoId.toString())
            args.putString("photoUrl", photoUrl)
            val fragment = DetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.detail_fragment, container, false)
        val image = view.findViewById<ImageView>(R.id.displayImageView)
        val imageName = view.findViewById<TextView>(R.id.imageNameTextView)
        val imageLikes = view.findViewById<TextView>(R.id.likeCountTextView)
        val photoIdTextView = view.findViewById<TextView>(R.id.photoIdTextView)

        imageName.text = arguments?.getString("photographer")
        imageLikes.text = arguments?.getString("likes")
        photoIdTextView.text = arguments?.getString("photoId")
        Picasso.get()
            .load(arguments?.getString("photoUrl")).into(image);
        return view
    }
}