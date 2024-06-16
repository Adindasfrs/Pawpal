package com.capstone.pawpal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstone.pawpal.R
import com.capstone.pawpal.dataclass.CatFood

class CatFoodAdapter(private val catFoodList: List<CatFood>) : RecyclerView.Adapter<CatFoodAdapter.CatFoodViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatFoodViewHolder {
        // Inflate the correct layout
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cat_food, parent, false)
        return CatFoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatFoodViewHolder, position: Int) {
        val catFood = catFoodList[position]
        holder.bind(catFood)
    }

    override fun getItemCount(): Int = catFoodList.size

    class CatFoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Initialize the TextViews using findViewById
        private val foodName: TextView = itemView.findViewById(R.id.foodName)
        private val foodDescription: TextView = itemView.findViewById(R.id.foodDescription)

        fun bind(catFood: CatFood) {
            // Set the text for the TextViews
            foodName.text = catFood.name
            foodDescription.text = catFood.description
        }
    }
}
