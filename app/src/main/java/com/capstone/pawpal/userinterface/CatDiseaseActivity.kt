package com.capstone.pawpal.userinterface

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.pawpal.adapter.CatDiseaseAdapter
import com.capstone.pawpal.dataclass.CatDisease
import kotlinx.android.synthetic.main.activity_cat_disease.*

class CatDiseaseActivity : AppCompatActivity(), CatDiseaseAdapter.OnItemClickListener {

    private lateinit var catDiseaseList: List<CatDisease>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_disease)

        // Inisialisasi RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inisialisasi data untuk RecyclerView
        catDiseaseList = listOf(
            CatDisease("Flu", "Flu is blablabla"),
            CatDisease("Fever", "Fever is blablabla"),
            // Tambahkan data lainnya sesuai kebutuhan
        )

        // Inisialisasi adapter untuk RecyclerView dengan listener
        val adapter = CatDiseaseAdapter(catDiseaseList, this)
        recyclerView.adapter = adapter

        // Implementasi onItemClick untuk menangani klik item di RecyclerView
        adapter.setOnItemClickListener { disease ->
            val intent = Intent(this, DetailCatDiseaseActivity::class.java)
            intent.putExtra("diseaseName", disease.name)
            intent.putExtra("diseaseDescription", disease.description)
            startActivity(intent)
        }
    }

    // Implementasi fungsi onItemClick dari antarmuka OnItemClickListener
    override fun onItemClick(catDisease: CatDisease) {
        // Disini bisa ditambahkan logika tambahan jika diperlukan
    }
}


