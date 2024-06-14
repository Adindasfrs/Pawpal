package com.capstone.pawpal.userinterface

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.pawpal.R

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton

class CatDiseaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_disease)

        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            onBackPressed()
        }
    }
}