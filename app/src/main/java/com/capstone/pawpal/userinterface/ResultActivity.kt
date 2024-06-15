package com.capstone.pawpal.userinterface

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
    }

    // Method untuk menangani klik panah kembali
    fun onBackClicked(view: android.view.View) {
        finish() // Contoh: tutup aktivitas jika panah kembali diklik
    }

    // Method untuk menangani klik tombol "Add"
    fun onAddClicked(view: android.view.View) {
        Toast.makeText(this, "Add button clicked", Toast.LENGTH_SHORT).show()
        // Tambahkan logika untuk tombol "Add" di sini
    }

    // Method untuk menangani klik tombol "Library"
    fun onLibraryClicked(view: android.view.View) {
        Toast.makeText(this, "Library button clicked", Toast.LENGTH_SHORT).show()
        // Tambahkan logika untuk tombol "Library" di sini
    }

    // Method untuk menangani klik tombol "Language"
    fun onLanguageClicked(view: android.view.View) {
        Toast.makeText(this, "Language button clicked", Toast.LENGTH_SHORT).show()
        // Tambahkan logika untuk tombol "Language" di sini
    }

    // Method untuk menangani klik tombol "Logout"
    fun onLogoutClicked(view: android.view.View) {
        Toast.makeText(this, "Logout button clicked", Toast.LENGTH_SHORT).show()
        // Tambahkan logika untuk tombol "Logout" di sini
    }
}

