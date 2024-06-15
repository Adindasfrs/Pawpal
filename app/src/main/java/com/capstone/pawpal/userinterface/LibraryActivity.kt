package com.capstone.pawpal.userinterface

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.capstone.pawpal.R

class LibraryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)
    }

    // Method untuk navigasi ke CatDiseaseActivity
    fun navigateToCatDiseaseActivity(view: View) {
        val intent = Intent(this, CatDiseaseActivity::class.java)
        startActivity(intent)
    }

    // Method untuk navigasi ke CatFoodActivity
    fun navigateToCatFoodActivity(view: View) {
        val intent = Intent(this, CatFoodActivity::class.java)
        startActivity(intent)
    }

    // Method untuk navigasi ke AddActivity
    fun navigateToAddActivity(view: View) {
        val intent = Intent(this, AddImageActivity::class.java)
        startActivity(intent)
    }

    // Method untuk navigasi ke LibraryActivity (tidak melakukan apa-apa karena sudah berada di LibraryActivity)
    fun navigateToLibraryActivity(view: View) {
        // Tidak perlu ada tindakan, karena sudah berada di LibraryActivity
    }

    // Method untuk navigasi ke LanguageActivity
    fun navigateToLanguageActivity(view: View) {
        val intent = Intent(this, LanguageActivity::class.java)
        startActivity(intent)
    }

    // Method untuk navigasi ke LoginActivity (contoh, dapat disesuaikan dengan activity logout yang sebenarnya)
    fun navigateToLogout(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Akhiri activity saat logout
    }
}
