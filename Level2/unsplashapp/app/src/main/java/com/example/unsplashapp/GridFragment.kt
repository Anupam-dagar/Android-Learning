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
    var imagearray = mutableListOf<PexelsDataPhoto>()
    lateinit var recyclerView: RecyclerView

    companion object {
        fun newInstance(): GridFragment {
            val fragment = GridFragment()
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.image_grid, container, false)
        recyclerView = view.findViewById(R.id.imageGridRecyclerView)
        val adapter = RecyclerViewDataAdapter(imagearray, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(inflater.context,3)
        getPexelsImages(inflater.context)
        return view
    }

    override fun onImageClick(position: Int) {
        val transaction = fragmentManager?.beginTransaction()
        val detailfragment = DetailFragment.newInstance(imagearray[position].photographer, "0", imagearray[position].id, imagearray[position].src.large)
        transaction?.replace(R.id.fragment_holder, detailfragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

    private fun getPexelsImages(context: Context) {
        val call: Call<PexelsDataResponse> = PexelsApiClient.getClient.getImages(40)
        call.enqueue(object : Callback<PexelsDataResponse> {

            override fun onResponse(
                call: Call<PexelsDataResponse>,
                response: Response<PexelsDataResponse>
            ) {
                imagearray.addAll(response!!.body()!!.photos)
                recyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<PexelsDataResponse>?, t: Throwable?) {
                Log.d("request", "$t")
                Toast.makeText(context, "An error occurred while getting images.", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}