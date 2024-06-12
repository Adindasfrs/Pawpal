package com.capstone.pawpal.userinterface

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.capstone.pawpal.UserPreferences
import com.capstone.pawpal.databinding.ActivitySplashScreenBinding
import com.capstone.pawpal.viewmodel.UserLoginViewModel
import com.capstone.pawpal.viewmodel.ViewModelFactory

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = UserPreferences.getInstance(dataStore)
        val loginViewModel =
            ViewModelProvider(this, ViewModelFactory(pref))[UserLoginViewModel::class.java]

        loginViewModel.getLoginSession().observe(this) { isLoggedIn ->

            val splashScreenTextBottom =
                ObjectAnimator.ofFloat(binding.bottomSplashScreen, View.ALPHA, 1f).setDuration(2000)

            AnimatorSet().apply {
                playTogether(splashScreenTextBottom)
                start()
            }

            // Check login status before starting MainActivity or HomeActivity
            val intent = if (isLoggedIn) {
                Intent(this, HomePageActivity::class.java)
            } else {
                Intent(this, MainActivity::class.java)
            }

            binding.bangkit.animate()
                .setDuration(3000)
                .alpha(0f)
                .withEndAction {
                    startActivity(intent)
                    finish()
                }
        }
    }
}