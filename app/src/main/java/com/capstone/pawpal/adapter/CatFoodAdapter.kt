package com.capstone.pawpal.adapter

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CatFoodAdapter(private val catFoods: List<String>) :
    RecyclerView.Adapter<CatFoodAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cat_food, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val catFood = catFoods[position]
        holder.textView.text = catFood
    }

    override fun getItemCount(): Int {
        return catFoods.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView
        var imageView: ImageView

        init {
            textView = itemView.findViewById<TextView>(R.id.textCatFood)
            imageView = itemView.findViewById<ImageView>(R.id.imageCatFood)
        }
    }
}

