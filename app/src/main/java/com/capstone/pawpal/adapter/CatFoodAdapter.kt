package com.capstone.pawpal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_cat_food.view.*

class CatFoodAdapter(private val catFoodList: List<CatFood>) : RecyclerView.Adapter<CatFoodAdapter.CatFoodViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatFoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cat_food, parent, false)
        return CatFoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatFoodViewHolder, position: Int) {
        val catFood = catFoodList[position]
        holder.bind(catFood)
    }

    override fun getItemCount(): Int = catFoodList.size

    class CatFoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(catFood: CatFood) {
            itemView.catFoodName.text = catFood.name
            itemView.catFoodDescription.text = catFood.description
        }
    }
}

data class CatFood(val name: String, val description: String)
