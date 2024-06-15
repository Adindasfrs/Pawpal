package com.capstone.pawpal.userinterface

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.pawpal.R
import com.capstone.pawpal.adapter.CatFood
import kotlinx.android.synthetic.main.activity_cat_food.*

class CatFoodActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_food)

        // Set OnClickListener untuk tombol kembali (backButton)
        backButton.setOnClickListener {
            onBackPressed() // Kembali ke activity sebelumnya
        }

        // Set OnClickListener untuk tombol Add
        addButton.setOnClickListener {
            // Implementasikan aksi untuk tombol Add
            val intent = Intent(this, AddImageActivity::class.java)
            startActivity(intent)
        }

        // Set OnClickListener untuk tombol Library
        libraryButton.setOnClickListener {
            // Implementasikan aksi untuk tombol Library
            val intent = Intent(this, LibraryActivity::class.java)
            startActivity(intent)
        }

        // Set OnClickListener untuk tombol Language
        languageButton.setOnClickListener {
            // Implementasikan aksi untuk tombol Language
            val intent = Intent(this, LanguageActivity::class.java)
            startActivity(intent)
        }

        // Set OnClickListener untuk tombol Logout
        logoutButton.setOnClickListener {
            // Implementasikan aksi untuk tombol Logout
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Inisialisasi RecyclerView dengan adapter
        // recyclerView.adapter = CatFoodAdapter(getCatFoodList()) // Pastikan Anda sudah memiliki adapter dan data list

        // Contoh data yang bisa Anda gunakan
        val catFoodList = listOf(
            CatFood("Brand A", "Description A"),
            CatFood("Brand B", "Description B")
        )

        // Inisialisasi adapter dengan data
        val adapter = CatFoodAdapter(catFoodList)
        recyclerView.adapter = adapter
    }
}
