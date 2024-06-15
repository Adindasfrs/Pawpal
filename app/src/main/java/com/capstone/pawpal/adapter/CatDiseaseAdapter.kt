package com.capstone.pawpal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstone.pawpal.R
import com.capstone.pawpal.dataclass.CatDisease

class CatDiseaseAdapter(private val catDiseaseList: List<CatDisease>,
                        private val listener: OnItemClickListener) :
    RecyclerView.Adapter<CatDiseaseAdapter.ViewHolder>() {

    // Antarmuka untuk menangani klik item
    interface OnItemClickListener {
        fun onItemClick(catDisease: CatDisease)
    }

    // ViewHolder untuk menyimpan referensi komponen tampilan
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val textName: TextView = itemView.findViewById(R.id.textName)
        val textDescription: TextView = itemView.findViewById(R.id.textDescription)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(catDiseaseList[position])
            }
        }
    }

    // Membuat ViewHolder baru untuk item yang diperlukan
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cat_disease, parent, false)
        return ViewHolder(itemView)
    }

    // Menghubungkan data dengan tampilan di ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val catDisease = catDiseaseList[position]
        holder.textName.text = catDisease.name
        holder.textDescription.text = catDisease.description
    }

    // Mengembalikan jumlah total item dalam daftar
    override fun getItemCount(): Int {
        return catDiseaseList.size
    }
}

