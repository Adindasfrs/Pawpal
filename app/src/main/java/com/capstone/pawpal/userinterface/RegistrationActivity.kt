package com.capstone.pawpal.userinterface

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.capstone.pawpal.R
import com.capstone.pawpal.UserPreferences
import com.capstone.pawpal.api.APIService
import com.capstone.pawpal.dataStore
import com.capstone.pawpal.databinding.ActivityRegistrationBinding
import com.capstone.pawpal.dataclass.LoginDataAccount
import com.capstone.pawpal.dataclass.RegisterDataAccount
import com.capstone.pawpal.retrofit.RetrofitClient
import com.capstone.pawpal.viewmodel.MainViewModel
import com.capstone.pawpal.viewmodel.UserLoginViewModel
import com.capstone.pawpal.viewmodel.ViewModelFactory

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var apiService: APIService
    private lateinit var mainViewModel: MainViewModel
    private lateinit var userLoginViewModel: UserLoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = resources.getString(R.string.createAccount)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Inisialisasi RetrofitClient
        apiService = RetrofitClient.instance.create(APIService::class.java)

        // Setup ViewModel
        setupViewModels()

        // Setup Click Listeners
        setupClickListeners()
    }

    private fun setupViewModels() {
        val pref = UserPreferences.getInstance(dataStore)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        userLoginViewModel = ViewModelProvider(this, ViewModelFactory(pref))[UserLoginViewModel::class.java]

        // Observe login session
        userLoginViewModel.getLoginSession().observe(this) { sessionTrue ->
            if (sessionTrue) {
                navigateToHome()
            }
        }

        // Observe registration response
        mainViewModel.messageRegist.observe(this) { messageRegist ->
            responseRegister(mainViewModel.isErrorRegist, messageRegist)
        }

        mainViewModel.isLoadingRegist.observe(this) { isLoading ->
            showLoading(isLoading)
        }

        // Observe login response
        mainViewModel.messageLogin.observe(this) { messageLogin ->
            responseLogin(mainViewModel.isErrorLogin, messageLogin)
        }

        mainViewModel.isLoadingLogin.observe(this) { isLoading ->
            showLoading(isLoading)
        }
    }

    private fun setupClickListeners() {
        binding.seeRegistPassword.setOnCheckedChangeListener { _, isChecked ->
            togglePasswordVisibility(isChecked)
        }

        binding.btnRegistAccount.setOnClickListener {
            clearFocusFromEditTexts()

            if (validateInput()) {
                val dataRegisterAccount = RegisterDataAccount(
                    name = binding.RegistName.text.toString().trim(),
                    email = binding.RegistEmail.text.toString().trim(),
                    password = binding.RegistPassword.text.toString().trim()
                )

                mainViewModel.getResponseRegister(dataRegisterAccount)
            } else {
                handleInvalidInput()
            }
        }
    }

    private fun togglePasswordVisibility(isChecked: Boolean) {
        val transformationMethod = if (isChecked) {
            HideReturnsTransformationMethod.getInstance()
        } else {
            PasswordTransformationMethod.getInstance()
        }

        binding.RegistPassword.transformationMethod = transformationMethod
        binding.RetypePassword.transformationMethod = transformationMethod

        binding.RegistPassword.setSelection(binding.RegistPassword.text?.length ?: 0)
        binding.RetypePassword.setSelection(binding.RetypePassword.text?.length ?: 0)
    }

    private fun clearFocusFromEditTexts() {
        binding.apply {
            RegistName.clearFocus()
            RegistEmail.clearFocus()
            RegistPassword.clearFocus()
            RetypePassword.clearFocus()
        }
    }

    private fun validateInput(): Boolean {
        return binding.RegistName.isNameValid &&
                binding.RegistEmail.isEmailValid &&
                binding.RegistPassword.isPasswordValid &&
                binding.RetypePassword.isPasswordValid
    }

    private fun handleInvalidInput() {
        if (!binding.RegistName.isNameValid) {
            binding.RegistName.error = resources.getString(R.string.nameNone)
        }
        if (!binding.RegistEmail.isEmailValid) {
            binding.RegistEmail.error = resources.getString(R.string.emailNone)
        }
        if (!binding.RegistPassword.isPasswordValid) {
            binding.RegistPassword.error = resources.getString(R.string.passwordNone)
        }
        if (!binding.RetypePassword.isPasswordValid) {
            binding.RetypePassword.error = resources.getString(R.string.passwordConfirmNone)
        }

        Toast.makeText(this, R.string.invalidLogin, Toast.LENGTH_SHORT).show()
    }

    private fun responseLogin(isError: Boolean, message: String) {
        if (!isError) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            userLoginViewModel.saveLoginSession(true)
            mainViewModel.userLogin.value?.let { user ->
                userLoginViewModel.saveToken(user.loginResult.token)
                userLoginViewModel.saveName(user.loginResult.name)
            }
            navigateToHome()
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun responseRegister(isError: Boolean, message: String) {
        if (!isError) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            val userLogin = LoginDataAccount(
                binding.RegistEmail.text.toString(),
                binding.RegistPassword.text.toString()
            )
            mainViewModel.getResponseLogin(userLogin)
        } else {
            if (message == "1") {
                binding.RegistEmail.setErrorMessage(
                    resources.getString(R.string.emailTaken),
                    binding.RegistEmail.text.toString()
                )
                Toast.makeText(this, resources.getString(R.string.emailTaken), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar2.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun navigateToHome() {
        val intent = Intent(this@RegistrationActivity, AddImageActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
