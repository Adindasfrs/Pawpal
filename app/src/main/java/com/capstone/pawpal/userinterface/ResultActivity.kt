package com.capstone.pawpal.userinterface

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.capstone.pawpal.R
import com.capstone.pawpal.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var resultBinding: ActivityResultBinding

    private lateinit var ivBackArrow: ImageView
    private lateinit var resultText: TextView

    private lateinit var addButton: LinearLayout
    private lateinit var libraryButton: LinearLayout
    private lateinit var logoutButton: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resultBinding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(resultBinding.root)

        ivBackArrow = findViewById(R.id.ivBackArrow)
        resultText = findViewById(R.id.result_text)

        addButton = findViewById(R.id.addButton)
        libraryButton = findViewById(R.id.libraryButton)
        logoutButton = findViewById(R.id.logoutButton)

        ivBackArrow.setOnClickListener { finish() }
        addButton.setOnClickListener { navigateToAdd() }
        libraryButton.setOnClickListener { navigateToLibrary() }
        logoutButton.setOnClickListener { navigateToLogout() }

        // Retrieve and display the analysis result
        val analyzedResult = intent.getStringExtra("ANALYSIS_RESULT")
        resultText.text = analyzedResult

    }

    private fun navigateToAdd() {
        // Navigate back to AddImageActivity
        val intent = Intent(this, AddImageActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToLibrary() {
        // Implement navigation logic to Library screen
        val intent = Intent(this, LibraryActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToLogout() {
        // Implement logout logic if needed
    }
}
