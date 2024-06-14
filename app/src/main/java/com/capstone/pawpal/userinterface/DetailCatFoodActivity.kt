package com.capstone.pawpal.userinterface

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.pawpal.R
import kotlinx.android.synthetic.main.activity_cat_disease.*

class DetailCatFoodActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_cat_food)

        backButton.setOnClickListener {
            finish() // Closes the activity and returns to the previous one
        }

        // You can set the data for the views here
        // For example:
        // title.text = "Cat Disease"
        // diseaseName.text = "Flu"
        // diseaseDescription.text = "Flu is blablabla"
    }
}