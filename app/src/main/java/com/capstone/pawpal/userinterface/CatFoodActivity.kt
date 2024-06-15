package com.capstone.pawpal.userinterface

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.pawpal.adapter.CatFoodAdapter


class CatFoodActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var adapter: CatFoodAdapter? = null
    private var catFoods: MutableList<String>? = null // Example data
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_food)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.setLayoutManager(LinearLayoutManager(this))

        // Initialize data
        catFoods = ArrayList()
        catFoods.add("Cat Food 1")
        catFoods.add("Cat Food 2")
        catFoods.add("Cat Food 3")
        // Add more items as needed

        // Initialize adapter
        adapter = CatFoodAdapter(catFoods)
        recyclerView.setAdapter(adapter)
    }
}

