package com.capstone.pawpal.userinterface

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.capstone.pawpal.R

class ResultActivity : AppCompatActivity() {

    private lateinit var ivBackArrow: ImageView
    private lateinit var resultImage: ImageView
    private lateinit var resultText: TextView

    private lateinit var addButton: LinearLayout
    private lateinit var libraryButton: LinearLayout
    private lateinit var languageButton: LinearLayout
    private lateinit var logoutButton: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        ivBackArrow = findViewById(R.id.ivBackArrow)
        resultImage = findViewById(R.id.result_image)
        resultText = findViewById(R.id.result_text)

        addButton = findViewById(R.id.addButton)
        libraryButton = findViewById(R.id.libraryButton)
        languageButton = findViewById(R.id.languageButton)
        logoutButton = findViewById(R.id.logoutButton)

        ivBackArrow.setOnClickListener { finish() }
        addButton.setOnClickListener { navigateToAdd() }
        libraryButton.setOnClickListener { navigateToLibrary() }
        languageButton.setOnClickListener { navigateToLanguage() }
        logoutButton.setOnClickListener { navigateToLogout() }

        // Example: Load result data
        // resultImage.setImageBitmap(...)
        // resultText.text = "Analysis Result: ..."
    }

    private fun navigateToAdd() {
        val intent = Intent(this, AddImageActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToLibrary() {
        // Example: Navigate to library activity
    }

    private fun navigateToLanguage() {
        // Example: Navigate to language settings activity
    }

    private fun navigateToLogout() {
        // Example: Handle logout
    }
}

