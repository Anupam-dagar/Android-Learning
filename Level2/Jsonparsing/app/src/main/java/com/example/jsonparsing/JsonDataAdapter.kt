package com.example.jsonparsing

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class JsonDataAdapter(
    private var context: Context,
    private var personNames: MutableList<String>,
    private var emailIds: MutableList<String>,
    private var mobileNumbers: MutableList<String>
) : RecyclerView.Adapter<JsonDataAdapter.JsonDataHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JsonDataHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_layout, parent, false)
        return JsonDataHolder(v)
    }

    override fun onBindViewHolder(holder: JsonDataHolder, position: Int) {
        holder.name.text = personNames[position]
        holder.email.text = emailIds[position]
        holder.mobileNo.text = mobileNumbers[position]
    }


    override fun getItemCount(): Int {
        return personNames.size
    }

    inner class JsonDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var name = itemView.findViewById<TextView>(R.id.name)
        internal var email = itemView.findViewById<TextView>(R.id.email)
        internal var mobileNo = itemView.findViewById<TextView>(R.id.mobileNo)
    }
}