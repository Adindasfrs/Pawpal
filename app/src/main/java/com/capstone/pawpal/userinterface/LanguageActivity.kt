package com.capstone.pawpal.userinterface

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.capstone.pawpal.R
import java.util.Locale

class LanguageActivity : AppCompatActivity() {

    private lateinit var englishItem: LinearLayout
    private lateinit var indonesiaItem: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

        // Set OnClickListener untuk tombol kembali (backButton)
        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
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


        englishItem = findViewById(R.id.englishItem)
        indonesiaItem = findViewById(R.id.indonesiaItem)

        englishItem.setOnClickListener {
            setLocale("en")
        }

        indonesiaItem.setOnClickListener {
            setLocale("id")
        }
    }



    private fun setLocale(langCode: String) {
        val locale = Locale(langCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        resources.updateConfiguration(config, resources.displayMetrics)

        // Restart the activity to apply the new language
        val intent = intent
        finish()
        startActivity(intent)
    }
}