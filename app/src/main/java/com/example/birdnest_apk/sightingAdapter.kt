package com.example.birdnest_apk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class sightingAdapter(private val signtinglist : ArrayList<savedSightings>) :RecyclerView.Adapter<sightingAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.sighting_item,
            parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return signtinglist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = signtinglist[position]

        holder.city.text = currentitem.city
        holder.locationName.text = currentitem.locationName
    }
  class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

      val city : TextView = itemView.findViewById(R.id.txtCityView)
      val locationName : TextView = itemView.findViewById(R.id.txtLocationView)
  }


}