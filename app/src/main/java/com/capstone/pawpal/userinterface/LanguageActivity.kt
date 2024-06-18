package com.capstone.pawpal.userinterface

import android.content.res.Configuration
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.capstone.pawpal.R
import java.util.Locale

class LanguageActivity : AppCompatActivity() {

    private lateinit var englishItem: LinearLayout
    private lateinit var indonesiaItem: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

        englishItem = findViewById(R.id.englishItem)
        indonesiaItem = findViewById(R.id.indonesiaItem)

        englishItem.setOnClickListener {
            setLocale("en")
        }

        indonesiaItem.setOnClickListener {
            setLocale("id")
        }
    }

    private fun setLocale(langCode: String) {
        val locale = Locale(langCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        resources.updateConfiguration(config, resources.displayMetrics)

        // Restart the activity to apply the new language
        val intent = intent
        finish()
        startActivity(intent)
    }
}