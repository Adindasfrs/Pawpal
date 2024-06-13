package com.capstone.pawpal.userinterface

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.pawpal.databinding.ActivityAnalyzeResultBinding

class AnalyzeResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnalyzeResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnalyzeResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil data hasil analisis dari Intent
        val analyzedResult = intent.getStringExtra("analyzedResult")
        val imageUri = intent.getStringExtra("imageUri")

        // Tampilkan hasil analisis
        binding.resultText.text = analyzedResult
        // Jika Anda ingin menampilkan gambar hasil dari URI, gunakan Glide atau Picasso di sini
        // Misalnya: Glide.with(this).load(imageUri).into(binding.resultImage)

        // Dummy placeholder untuk gambar
        binding.resultImage.setImageResource(R.drawable.default_image) // Ganti dengan gambar hasilnya jika ada
    }
}
