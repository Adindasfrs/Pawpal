package com.capstone.pawpal.userinterface

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.capstone.pawpal.R

class CatDiseaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_disease)

        val backButton: ImageButton = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            // Handle back button click
            finish()
        }
    }
}