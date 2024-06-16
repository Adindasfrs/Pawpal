package com.capstone.pawpal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstone.pawpal.databinding.ItemCatDiseaseBinding
import com.capstone.pawpal.dataclass.CatDisease

class CatDiseaseAdapter(
    private val catDiseaseList: List<CatDisease>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<CatDiseaseAdapter.CatDiseaseViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(catDisease: CatDisease)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatDiseaseViewHolder {
        val binding = ItemCatDiseaseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatDiseaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatDiseaseViewHolder, position: Int) {
        val catDisease = catDiseaseList[position]
        holder.bind(catDisease)
    }

    override fun getItemCount(): Int = catDiseaseList.size

    inner class CatDiseaseViewHolder(private val binding: ItemCatDiseaseBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(catDiseaseList[position])
                }
            }
        }

        fun bind(catDisease: CatDisease) {
            binding.foodName.text = catDisease.name
            binding.foodDescription.text = catDisease.description
        }

    }
}
