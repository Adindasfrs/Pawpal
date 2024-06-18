package com.capstone.pawpal.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstone.pawpal.R
import com.capstone.pawpal.dataclass.CatDisease
import com.capstone.pawpal.dataclass.CatFood
import com.capstone.pawpal.userinterface.DetailCatFoodActivity

class CatFoodAdapter(private val context: Context, private val catFoodList: List<CatFood>) :
    RecyclerView.Adapter<CatFoodAdapter.CatFoodViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(catDisease: CatDisease)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatFoodViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_cat_food, parent, false)
        return CatFoodViewHolder(view)
    }



    override fun onBindViewHolder(holder: CatFoodViewHolder, position: Int) {
        val catFood = catFoodList[position]
        holder.bind(catFood)
    }

    override fun getItemCount(): Int = catFoodList.size

    inner class CatFoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val foodName: TextView = itemView.findViewById(R.id.foodName)
        private val foodDescription: TextView = itemView.findViewById(R.id.foodDescription)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(catFood: CatFood) {
            foodName.text = catFood.name
            foodDescription.text = catFood.description
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val catFood = catFoodList[position]
                val intent = Intent(context, DetailCatFoodActivity::class.java).apply {
                    putExtra("FOOD_NAME", catFood.name)
                    putExtra("FOOD_DESCRIPTION", catFood.description)
                }
                context.startActivity(intent)
            }
        }
    }
}
