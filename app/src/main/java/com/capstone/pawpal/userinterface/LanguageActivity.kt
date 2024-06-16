package com.capstone.pawpal.userinterface

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.pawpal.databinding.ActivityLanguageBinding
import java.util.Locale

class LanguageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLanguageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set OnClickListener untuk tombol kembali (backButton)
        binding.backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Set OnClickListener untuk item bahasa
        binding.englishItem.setOnClickListener {
            setLocale("en")
            recreate()
        }
        binding.indonesiaItem.setOnClickListener {
            setLocale("id")
            recreate()
        }

        // Set OnClickListener untuk tombol Add
        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddImageActivity::class.java)
            startActivity(intent)
        }

        // Set OnClickListener untuk tombol Library
        binding.libraryButton.setOnClickListener {
            val intent = Intent(this, LibraryActivity::class.java)
            startActivity(intent)
        }

        // Set OnClickListener untuk tombol Language
        binding.languageButton.setOnClickListener {
            val intent = Intent(this, LanguageActivity::class.java)
            startActivity(intent)
        }

        // Set OnClickListener untuk tombol Logout
        binding.logoutButton.setOnClickListener {
            // Menghapus preferensi bahasa yang tersimpan
            val sharedPrefs = getSharedPreferences("Settings", Context.MODE_PRIVATE)
            sharedPrefs.edit().remove("My_Lang").apply()

            // Navigasi ke halaman Login
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }

    private fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        // Memperbarui context aplikasi dengan konfigurasi baru
        val context = createConfigurationContext(config)
        resources.updateConfiguration(config, context.resources.displayMetrics)
    }

    override fun attachBaseContext(newBase: Context) {
        val sharedPrefs = newBase.getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val languageCode = sharedPrefs.getString("My_Lang", "en")
        val locale = Locale(languageCode!!)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        val context = newBase.createConfigurationContext(config)
        super.attachBaseContext(context)
    }
}
