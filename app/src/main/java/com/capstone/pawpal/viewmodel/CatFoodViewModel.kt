package com.capstone.pawpal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.pawpal.Repository

class CatFoodViewModel(private val repository: Repository) : ViewModel(){
    fun CatBreeds(listPenyakit: String, idRas: Int, definisi: String, gambar: String, namaRas: String) =
        repository.CatBreeds(listPenyakit, idRas, definisi, gambar, namaRas).asLiveData()
}