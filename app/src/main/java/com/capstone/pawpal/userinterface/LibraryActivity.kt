package com.capstone.pawpal.userinterface

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton
import android.widget.LinearLayout

class LibraryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)

        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            onBackPressed()
        }

        val catDiseaseItem: LinearLayout = findViewById(R.id.catDiseaseItem)
        catDiseaseItem.setOnClickListener {
            val intent = Intent(this, CatDiseaseActivity::class.java)
            startActivity(intent)
        }
    }
}