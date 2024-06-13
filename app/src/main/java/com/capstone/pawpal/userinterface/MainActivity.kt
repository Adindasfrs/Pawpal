package com.capstone.pawpal.userinterface

import android.Manifest
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.capstone.pawpal.R
import com.capstone.pawpal.UserPreferences
import com.capstone.pawpal.databinding.ActivityMainBinding
import com.capstone.pawpal.dataclass.LoginDataAccount
import com.capstone.pawpal.helper.ImageClassifierHelper
import com.capstone.pawpal.viewmodel.MainViewModel
import com.capstone.pawpal.viewmodel.UserLoginViewModel
import com.capstone.pawpal.viewmodel.ViewModelFactory
import java.text.NumberFormat

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var imageClassifierHelper: ImageClassifierHelper

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private var displayResult: String? = null
    private var currentImageUri: Uri? = null

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(this, "Permission request granted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Permission request denied", Toast.LENGTH_LONG).show()
            }
        }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            this,
            REQUIRED_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!allPermissionsGranted()) {
            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
        }

        ifClicked()
        playAnimation()

        val preferences = UserPreferences.getInstance(dataStore)
        val userLoginViewModel =
            ViewModelProvider(this, ViewModelFactory(preferences))[UserLoginViewModel::class.java]

        userLoginViewModel.getLoginSession().observe(this) { sessionTrue ->
            if (sessionTrue) {
                val intent = Intent(this@MainActivity, AddImageActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        mainViewModel.messageLogin.observe(this) { message ->
            responseLogin(
                mainViewModel.isErrorLogin,
                message,
                userLoginViewModel
            )
        }

        mainViewModel.isLoadingLogin.observe(this) {
            showLoading(it)
        }

        binding.galleryButton.setOnClickListener { startGallery() }
        binding.analyzeButton.setOnClickListener {
            currentImageUri?.let {
                analyzeImage()
            } ?: run {
                showToast(getString(R.string.empty_image_warning))
            }
        }
    }

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImage(uri)
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_GALLERY && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                currentImageUri = uri
                showImage(currentImageUri)
            }
        }
    }

    private fun showImage(uri: Uri?) {
        uri?.let {
            binding.imageStoryUpload.setImageURI(it)
        }
    }

    private fun analyzeImage() {
        imageClassifierHelper = ImageClassifierHelper(
            context = this,
            classifierListener = object : ImageClassifierHelper.ClassifierListener {
                override fun onError(error: String) {
                    runOnUiThread {
                        showToast(error)
                    }
                }

                override fun onResults(results: List<Classifications>?) {
                    runOnUiThread {
                        results?.let { it ->
                            if (it.isNotEmpty() && it[0].categories.isNotEmpty()) {
                                println(it)
                                val sortedCategories =
                                    it[0].categories.sortedByDescending { it?.score }
                                displayResult =
                                    sortedCategories.joinToString("\n") {
                                        "${it.label} " + NumberFormat.getPercentInstance()
                                            .format(it.score).trim()
                                    }
                                resultAnalyze()
                            } else {
                                displayResult = ""
                            }
                        }
                    }
                }
            }
        )
        imageClassifierHelper.classifyStaticImage(currentImageUri!!)
    }

    private fun resultAnalyze(){
        val intent = Intent(this@MainActivity, ResultActivity::class.java)
        intent.putExtra(ResultActivity.EXTRA_IMAGE_URI, currentImageUri.toString())
        intent.putExtra(ResultActivity.EXTRA_RESULT, displayResult.toString())
        startActivity(intent)
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
            } else {
                if (!binding.CVEmail.isEmailValid) binding.CVEmail.error =
                    getString(R.string.emailNone)
                if (!binding.PasswordLogin.isPasswordValid) binding.PasswordLogin.error =
                    getString(R.string.passwordNone)

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

    private fun responseLogin(
        isError: Boolean,
        message: String,
        userLoginViewModel: UserLoginViewModel
    ) {
        if (!isError) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            val user = mainViewModel.userLogin.value
            userLoginViewModel.saveLoginSession(true)
            userLoginViewModel.saveToken(user?.loginResult!!.token)
            userLoginViewModel.saveName(user.loginResult.name)
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.iconLogin, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val tvLoginDesc =
            ObjectAnimator.ofFloat(binding.tvLoginDescription, View.ALPHA, 1f).setDuration(300)
        val tvLogin = ObjectAnimator.ofFloat(binding.tvLogin, View.ALPHA, 1f).setDuration(300)
        val evEmail = ObjectAnimator.ofFloat(binding.CVEmail, View.ALPHA, 1f).setDuration(300)
        val evPassword =
            ObjectAnimator.ofFloat(binding.PasswordLogin, View.ALPHA, 1f).setDuration(300)
        val seePassword =
            ObjectAnimator.ofFloat(binding.seePassword, View.ALPHA, 1f).setDuration(300)
        val btnLogin = ObjectAnimator.ofFloat(binding.btnLogin, View.ALPHA, 1f).setDuration(300)
        val btnRegister =
            ObjectAnimator.ofFloat(binding.btnRegister, View.ALPHA, 1f).setDuration(300)
        val tvRegistDesc =
            ObjectAnimator.ofFloat(binding.tvRegistDescription, View.ALPHA, 1f).setDuration(300)

        AnimatorSet().apply {
            playSequentially(
                tvLoginDesc,
                tvLogin,
                evEmail,
                evPassword,
                seePassword,
                btnLogin,
                btnRegister,
                tvRegistDesc
            )
            start()
        }
    }

    private fun isDataValid(): Boolean {
        return binding.CVEmail.isEmailValid && binding.PasswordLogin.isPasswordValid
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(this)
    }

    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.READ_EXTERNAL_STORAGE
        private const val REQUEST_IMAGE_GALLERY = 100
    }
}