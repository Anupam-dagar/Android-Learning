package com.example.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class DataAdapter(private var cities: MutableList<String>, private var descriptions: MutableList<String>, private var cityImage: Int): RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    class DataViewHolder(view:  View): RecyclerView.ViewHolder(view) {
        val cityView: TextView
        val descriptionView: TextView
        val cityImageView: ImageView

        init {
            cityView = view.findViewById<TextView>(R.id.cityTitle)
            descriptionView = view.findViewById<TextView>(R.id.descriptionBody)
            cityImageView = view.findViewById<ImageView>(R.id.cityImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAdapter.DataViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        var view = layoutInflater.inflate(R.layout.recyclerview_row, parent, false)
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    override fun onBindViewHolder(holder: DataAdapter.DataViewHolder, position: Int) {
        holder.cityView.text = cities[position]
        holder.descriptionView.text = descriptions[position]
        holder.cityImageView.setImageResource(cityImage )
    }

    fun clear() {
        Log.d("adapter", "inside clear")
        cities.clear()
        descriptions.clear()
        notifyDataSetChanged()
    }

    fun addAll() {
        Log.d("adapter", "inside addall")
        cities.add("India")
        cities.add("India")
        cities.add("India")
        cities.add("India")

        descriptions.add("Refreshed Data")
        descriptions.add("Refreshed Data")
        descriptions.add("Refreshed Data")
        descriptions.add("Refreshed Data")
        notifyDataSetChanged()
    }
}