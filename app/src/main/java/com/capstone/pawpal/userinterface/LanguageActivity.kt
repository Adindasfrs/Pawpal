package com.capstone.pawpal.userinterface

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.capstone.pawpal.R
import java.util.*

class LanguageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)
    }

    fun onLanguageItemClick(view: View) {
        when (view.id) {
            R.id.englishItem -> {
                setLocale("en")
                recreate() // Restart activity to apply language change
            }
            R.id.indonesiaItem -> {
                setLocale("in")
                recreate() // Restart activity to apply language change
            }
        }
    }

    private fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        // Simpan preferensi bahasa di Shared Preferences jika perlu
        val sharedPrefs = getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.putString("My_Lang", languageCode)
        editor.apply()
    }

    override fun onResume() {
        super.onResume()
        loadLocale() // Load saved language preference
    }

    private fun loadLocale() {
        val sharedPrefs = getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val language = sharedPrefs.getString("My_Lang", "")
        if (language != null) {
            setLocale(language)
        }
    }

    // Override onDestroy to clear references to avoid memory leaks
    override fun onDestroy() {
        super.onDestroy()
        // Clear any references if necessary
    }
}
