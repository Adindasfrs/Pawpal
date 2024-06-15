package com.capstone.pawpal.userinterface

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.yourappname.R

class AddImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_image)

        // Set OnClickListener untuk tombol kembali (ivBackArrow)
        val backArrow: ImageView = findViewById(R.id.ivBackArrow)
        backArrow.setOnClickListener {
            onBackPressed() // Kembali ke activity sebelumnya
        }

        // Set OnClickListener untuk tombol Add
        val addButton: LinearLayout = findViewById(R.id.addButton)
        addButton.setOnClickListener {
            // Implementasikan aksi untuk tombol Add
            val intent = Intent(this, AddImageActivity::class.java)
            startActivity(intent)
        }

        // Set OnClickListener untuk tombol Library
        val libraryButton: LinearLayout = findViewById(R.id.libraryButton)
        libraryButton.setOnClickListener {
            // Implementasikan aksi untuk tombol Library
            val intent = Intent(this, LibraryActivity::class.java)
            startActivity(intent)
        }

        // Set OnClickListener untuk tombol Language
        val languageButton: LinearLayout = findViewById(R.id.languageButton)
        languageButton.setOnClickListener {
            // Implementasikan aksi untuk tombol Language
            val intent = Intent(this, LanguageActivity::class.java)
            startActivity(intent)
        }

        // Set OnClickListener untuk tombol Logout
        val logoutButton: LinearLayout = findViewById(R.id.logoutButton)
        logoutButton.setOnClickListener {
            // Implementasikan aksi untuk tombol Logout
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Menutup activity saat ini
        }
    }
}


