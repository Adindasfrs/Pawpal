package com.capstone.pawpal.userinterface

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.capstone.pawpal.R
import com.capstone.pawpal.dataclass.CatBreed
import com.capstone.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultActivity : AppCompatActivity() {

    private lateinit var ivBackArrow: ImageView
    private lateinit var resultImage: ImageView
    private lateinit var resultText: TextView

    private lateinit var addButton: LinearLayout
    private lateinit var libraryButton: LinearLayout
    private lateinit var languageButton: LinearLayout
    private lateinit var logoutButton: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)



        ivBackArrow = findViewById(R.id.ivBackArrow)
        resultImage = findViewById(R.id.result_image)
        resultText = findViewById(R.id.result_text)

        addButton = findViewById(R.id.addButton)
        libraryButton = findViewById(R.id.libraryButton)
        languageButton = findViewById(R.id.languageButton)
        logoutButton = findViewById(R.id.logoutButton)

        ivBackArrow.setOnClickListener { finish() }
        addButton.setOnClickListener { navigateToAdd() }
        libraryButton.setOnClickListener { navigateToLibrary() }
        languageButton.setOnClickListener { navigateToLanguage() }
        logoutButton.setOnClickListener { navigateToLogout() }

        // Retrieve and display the result data
        val resultData = intent.getStringExtra("RESULT_DATA")
        resultText.text = resultData

        // Example: Load result data
        // resultImage.setImageBitmap(...)

        fetchCatBreeds()
    }



    private fun navigateToAdd() {
        val intent = Intent(this, AddImageActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToLibrary() {
        // Example: Navigate to library activity
    }

    private fun navigateToLanguage() {
        // Example: Navigate to language settings activity
    }

    private fun navigateToLogout() {
        // Example: Handle logout
    }

    private fun fetchCatBreeds() {
        RetrofitClient.apiService.getCatBreeds().enqueue(object : Callback<List<CatBreed>> {
            override fun onResponse(call: Call<List<CatBreed>>, response: Response<List<CatBreed>>) {
                if (response.isSuccessful) {
                    val catBreeds = response.body()
                    // Handle the list of cat breeds
                    Log.d("ResultActivity", "Cat Breeds: $catBreeds")
                } else {
                    Log.e("ResultActivity", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<CatBreed>>, t: Throwable) {
                Log.e("ResultActivity", "Failed to fetch cat breeds", t)
                Toast.makeText(this@ResultActivity, "Failed to fetch cat breeds", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
