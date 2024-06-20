package com.capstone.pawpal.userinterface

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.pawpal.adapter.CatFoodAdapter
import com.capstone.pawpal.databinding.ActivityCatFoodBinding
import com.capstone.pawpal.dataclass.CatFood

class CatFoodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCatFoodBinding
    private lateinit var catFoodAdapter: CatFoodAdapter

//    private val viewModel: CatFoodViewModel by viewModels {
//        ViewModelFactory.useViewModelFactory {
//            CatFoodViewModel(Injection.provideRepository(this))
//        }
//    }

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

        // Set OnClickListener untuk tombol Logout
        binding.logoutButton.setOnClickListener {
            // Implementasikan aksi untuk tombol Logout
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Inisialisasi RecyclerView dengan adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // Inisialisasi data cat food
        val catFoodList = listOf(
            CatFood("Brand A", "Description A"),
            CatFood("Brand B", "Description B")
        )

//        fun catbreeds() {
//            viewModel.CatBreeds.collect { result ->
//                when (result) {
//                    is Result.Loading -> {
//                        // Show loading indicator
//                    }
//                    is Result.Success -> {
//                        val sortedBreeds = result.data.sortedBy { it.namaRas } // Sort by namaRas
//                        val adapter = CatBreedsAdapter(sortedBreeds)
//                        binding.recyclerView.adapter = adapter
//                    }
//                    is Result.Error -> {
//                        // Handle error
//                    }
//                }
//            }
//        }



        // Inisialisasi adapter dengan data
        catFoodAdapter = CatFoodAdapter(this, catFoodList) // Pastikan Anda memberikan Context yang benar
        binding.recyclerView.adapter = catFoodAdapter
    }
}
