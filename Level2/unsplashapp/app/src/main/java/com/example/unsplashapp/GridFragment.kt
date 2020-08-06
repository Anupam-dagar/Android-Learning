package com.example.unsplashapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GridFragment : Fragment(), RecyclerViewDataAdapter.OnImageClickListener {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: RecyclerViewDataAdapter

    companion object {
        fun newInstance(): GridFragment {
            return GridFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.image_grid, container, false)
        recyclerView = view.findViewById(R.id.imageGridRecyclerView)
        adapter = RecyclerViewDataAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(inflater.context, 3)
        getPexelsImages(inflater.context)
        return view
    }

    override fun onImageClick(imageData: PexelsDataPhoto, likes: String) {
        val transaction = fragmentManager?.beginTransaction()
        val detailfragment = DetailFragment.newInstance(
            imageData.photographer,
            likes,
            imageData.id,
            imageData.src.large
        )
        transaction?.replace(R.id.fragment_holder, detailfragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

    override fun onLikeButtonClick(imageData: PexelsDataPhoto, currentLikes: Int, photoId: String, position: Int) {
        adapter.likeImage(currentLikes, photoId, position)
        var detailfragment = fragmentManager?.findFragmentById(R.id.fragment_holder)
        if (detailfragment != null && detailfragment is DetailFragment) {
            detailfragment = DetailFragment.newInstance(
                imageData.photographer,
                currentLikes.plus(1).toString(),
                imageData.id,
                imageData.src.large
            )
            val transaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.fragment_holder, detailfragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun getPexelsImages(context: Context) {
        val call: Call<PexelsDataResponse> = PexelsApiClient.getClient.getImages(40)
        call.enqueue(object : Callback<PexelsDataResponse> {

            override fun onResponse(
                call: Call<PexelsDataResponse>,
                response: Response<PexelsDataResponse>
            ) {
                adapter.addImageData(response.body()!!.photos)
            }

            override fun onFailure(call: Call<PexelsDataResponse>?, t: Throwable?) {
                Log.d("request", "$t")
                Toast.makeText(
                    context,
                    "An error occurred while getting images.",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        })
    }
}