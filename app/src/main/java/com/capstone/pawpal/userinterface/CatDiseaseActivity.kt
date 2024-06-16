package com.capstone.pawpal.userinterface

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.pawpal.R
import com.capstone.pawpal.adapter.CatDiseaseAdapter
import com.capstone.pawpal.dataclass.CatDisease
import com.capstone.pawpal.databinding.ActivityCatDiseaseBinding

class CatDiseaseActivity : AppCompatActivity(), CatDiseaseAdapter.OnItemClickListener {

    private lateinit var catDiseaseList: List<CatDisease>
    private lateinit var binding: ActivityCatDiseaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatDiseaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set OnClickListener untuk tombol kembali (ivBackArrow)
        val backArrow: ImageView = findViewById(R.id.ivBackArrow)
        backArrow.setOnClickListener {
            onBackPressed() // Kembali ke activity sebelumnya
        }

        // Inisialisasi RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // Inisialisasi data untuk RecyclerView
        catDiseaseList = listOf(
            CatDisease("Flu", "Flu is blablabla"),
            CatDisease("Fever", "Fever is blablabla")
            // Tambahkan data lainnya sesuai kebutuhan
        )

        // Inisialisasi adapter untuk RecyclerView dengan listener
        val adapter = CatDiseaseAdapter(catDiseaseList, this)
        binding.recyclerView.adapter = adapter
    }

    // Implementasi fungsi onItemClick dari antarmuka OnItemClickListener
    override fun onItemClick(catDisease: CatDisease) {
        val intent = Intent(this, DetailCatDiseaseActivity::class.java)
        intent.putExtra("diseaseName", catDisease.name)
        intent.putExtra("diseaseDescription", catDisease.description)
        startActivity(intent)
    }
}
