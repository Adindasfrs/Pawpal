package com.capstone.pawpal.userinterface

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.capstone.pawpal.R
import com.capstone.pawpal.UserPreferences
import com.capstone.pawpal.dataStore
import com.capstone.pawpal.databinding.ActivityMainBinding
import com.capstone.pawpal.dataclass.LoginDataAccount
import com.capstone.pawpal.viewmodel.MainViewModel
import com.capstone.pawpal.viewmodel.UserLoginViewModel
import com.capstone.pawpal.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val userLoginViewModel: UserLoginViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory(UserPreferences.getInstance(dataStore)))[UserLoginViewModel::class.java]
    }
    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ifClicked()
        playAnimation()

//        userLoginViewModel.getLoginSession().observe(this) { sessionTrue ->
//            if (sessionTrue) {
//                val intent = Intent(this@MainActivity, AddImageActivity::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                startActivity(intent)
//            }
//        }

//        mainViewModel.messageLogin.observe(this) { message ->
  //          responseLogin(mainViewModel.isErrorLogin, message, userLoginViewModel)
    //    }

        mainViewModel.isLoadingLogin.observe(this) {
            showLoading(it)
        }
    }

    private fun responseLogin(isError: Boolean, message: String, userLoginViewModel: UserLoginViewModel) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        if (!isError) {
            mainViewModel.userLogin.value?.let { user ->
                user.loginResult?.let { loginResult ->
                    userLoginViewModel.saveLoginSession(true)
                    userLoginViewModel.saveToken(loginResult.token)
                    val intent = Intent(this@MainActivity, AddImageActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } ?: run {
                    Toast.makeText(this, "Login result is null", Toast.LENGTH_SHORT).show()
                    Log.e("LoginError", "Login result is null")
                }
            }
        }
    }


    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.iconLogin, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val tvLoginDesc = ObjectAnimator.ofFloat(binding.tvLoginDescription, View.ALPHA, 1f).setDuration(300)
        val evEmail = ObjectAnimator.ofFloat(binding.CVEmail, View.ALPHA, 1f).setDuration(300)
        val evPassword = ObjectAnimator.ofFloat(binding.PasswordLogin, View.ALPHA, 1f).setDuration(300)
        val seePassword = ObjectAnimator.ofFloat(binding.seePassword, View.ALPHA, 1f).setDuration(300)
        val btnLogin = ObjectAnimator.ofFloat(binding.btnLogin, View.ALPHA, 1f).setDuration(300)
        val btnRegister = ObjectAnimator.ofFloat(binding.btnRegister, View.ALPHA, 1f).setDuration(300)
        val tvRegistDesc = ObjectAnimator.ofFloat(binding.tvRegistDescription, View.ALPHA, 1f).setDuration(300)

        AnimatorSet().apply {
            playSequentially(tvLoginDesc, evEmail, evPassword, seePassword, btnLogin, btnRegister, tvRegistDesc)
            start()
        }
    }

    private fun ifClicked() {
        binding.btnLogin.setOnClickListener {
            binding.CVEmail.clearFocus()
            binding.PasswordLogin.clearFocus()

            if (isDataValid()) {
                val requestLogin = LoginDataAccount(
                    binding.CVEmail.text.toString().trim(),
                    binding.PasswordLogin.text.toString().trim()
                )
                mainViewModel.getResponseLogin(requestLogin)

                val intent = Intent(this@MainActivity, AddImageActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)

            } else {
                if (!binding.CVEmail.isEmailValid) binding.CVEmail.error = getString(R.string.emailNone)
                if (!binding.PasswordLogin.isPasswordValid) binding.PasswordLogin.error = getString(R.string.passwordNone)

                Toast.makeText(this, R.string.invalidLogin, Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

        binding.seePassword.setOnCheckedChangeListener { _, isChecked ->
            binding.PasswordLogin.transformationMethod = if (isChecked) {
                HideReturnsTransformationMethod.getInstance()
            } else {
                PasswordTransformationMethod.getInstance()
            }
            binding.PasswordLogin.text?.let { binding.PasswordLogin.setSelection(it.length) }
        }
    }

    private fun isDataValid(): Boolean {
        return binding.CVEmail.isEmailValid && binding.PasswordLogin.isPasswordValid
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
