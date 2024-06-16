package com.capstone.pawpal.userinterface

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.pawpal.adapter.CatFoodAdapter
import com.capstone.pawpal.dataclass.CatFood
import com.capstone.pawpal.databinding.ActivityCatFoodBinding

class CatFoodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCatFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set OnClickListener untuk tombol kembali (backButton)
        binding.backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed() // Kembali ke activity sebelumnya dengan metode baru
        }

        // Set OnClickListener untuk tombol Add
        binding.addButton.setOnClickListener {
            // Implementasikan aksi untuk tombol Add
            val intent = Intent(this, AddImageActivity::class.java)
            startActivity(intent)
        }

        // Set OnClickListener untuk tombol Library
        binding.libraryButton.setOnClickListener {
            // Implementasikan aksi untuk tombol Library
            val intent = Intent(this, LibraryActivity::class.java)
            startActivity(intent)
        }

        // Set OnClickListener untuk tombol Language
        binding.languageButton.setOnClickListener {
            // Implementasikan aksi untuk tombol Language
            val intent = Intent(this, LanguageActivity::class.java)
            startActivity(intent)
        }

        // Set OnClickListener untuk tombol Logout
        binding.logoutButton.setOnClickListener {
            // Implementasikan aksi untuk tombol Logout
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Inisialisasi RecyclerView dengan adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val catFoodList = listOf(
            CatFood("Brand A", "Description A"),
            CatFood("Brand B", "Description B")
        )

        // Inisialisasi adapter dengan data
        val adapter = CatFoodAdapter(catFoodList)
        binding.recyclerView.adapter = adapter
    }
}
