package com.capstone.pawpal.userinterface

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.pawpal.R
import com.capstone.pawpal.adapter.CatDiseaseAdapter
import com.capstone.pawpal.dataclass.CatDisease

class CatDiseaseActivity : AppCompatActivity(), CatDiseaseAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CatDiseaseAdapter
    private lateinit var catDiseaseList: List<CatDisease>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_disease)

        // Inisialisasi RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inisialisasi data untuk RecyclerView
        catDiseaseList = listOf(
            CatDisease("Disease 1", "Description of Disease 1"),
            CatDisease("Disease 2", "Description of Disease 2"),
            // Tambahkan data lainnya sesuai kebutuhan
        )

        // Inisialisasi adapter untuk RecyclerView dengan listener
        adapter = CatDiseaseAdapter(catDiseaseList, this)
        recyclerView.adapter = adapter
    }

    // Implementasi fungsi onItemClick dari antarmuka OnItemClickListener
    override fun onItemClick(catDisease: CatDisease) =
    // Aksi yang dilakukan saat item diklik
    // Misalnya, tampilkan pesan atau navigasi ke halaman detail
        // Di sini Anda dapat menangani logika untuk menanggapi klik item
        Unit
}

