package com.capstone.pawpal.userinterface

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.capstone.pawpal.R
import com.capstone.pawpal.UserPreferences
import com.capstone.pawpal.dataStore
import com.capstone.pawpal.viewmodel.UserLoginViewModel

class AddImageActivity : AppCompatActivity() {

    private lateinit var ivBackArrow: ImageView
    private lateinit var imageDetectionUpload: ImageView
    private lateinit var cameraButton: Button
    private lateinit var galleryButton: Button
    private lateinit var analyzeButton: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var addButton: LinearLayout
    private lateinit var libraryButton: LinearLayout
    private lateinit var logoutButton: LinearLayout

    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_IMAGE_PICK = 2
    private val REQUEST_CAMERA_PERMISSION = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_image)

        ivBackArrow = findViewById(R.id.ivBackArrow)
        imageDetectionUpload = findViewById(R.id.imageDetectionUpload)
        cameraButton = findViewById(R.id.cameraButton)
        galleryButton = findViewById(R.id.galleryButton)
        analyzeButton = findViewById(R.id.analyzeButton)
        progressBar = findViewById(R.id.progressBarAddStory)
        addButton = findViewById(R.id.addButton)
        libraryButton = findViewById(R.id.libraryButton)
        logoutButton = findViewById(R.id.logoutButton)

        ivBackArrow.setOnClickListener { finish() }
        cameraButton.setOnClickListener { openCamera() }
        galleryButton.setOnClickListener { openGallery() }
        analyzeButton.setOnClickListener { analyzeImage() }

        // Set onClickListeners for navigation bar buttons
        addButton.setOnClickListener { navigateToAdd() }
        libraryButton.setOnClickListener { navigateToLibrary() }
        logoutButton.setOnClickListener { logout() }
    }

    private fun openCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA_PERMISSION)
        } else {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (takePictureIntent.resolveActivity(packageManager) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }

    private fun analyzeImage() {
        // Implement your analyze image logic here
        progressBar.visibility = ProgressBar.VISIBLE
        // Example: Simulate analysis process
        imageDetectionUpload.postDelayed({
            val analyzedResult = "Breeds Of Cat:" // Replace with actual analysis result
            progressBar.visibility = ProgressBar.GONE
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("ANALYSIS_RESULT", analyzedResult)
            startActivity(intent)
        }, 2000) // Simulate 2 seconds delay
    }

    private fun navigateToAdd() {
        // Already on AddImageActivity, do nothing or update logic as needed
    }

    private fun navigateToLibrary() {
        // Implement navigation logic to Library screen
        val intent = Intent(this, LibraryActivity::class.java)
        startActivity(intent)
    }

    private fun logout() {
        // Implement logout logic
        // For example, clear user session and navigate to login screen
        val pref = UserPreferences.getInstance(dataStore)
        val userLoginViewModel = ViewModelProvider(this,
            com.capstone.pawpal.viewmodel.ViewModelFactory(pref)
        )[UserLoginViewModel::class.java]

        // Clear user session
        userLoginViewModel.saveLoginSession(false)
        userLoginViewModel.saveToken("")
        userLoginViewModel.saveName("")

        // Navigate to login screen (MainActivity in this example)
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish() // Optional: Finish current activity to prevent user from returning using Back button
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                openCamera()
            } else {
                // Permission denied
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    imageDetectionUpload.setImageBitmap(imageBitmap)
                }
                REQUEST_IMAGE_PICK -> {
                    val imageUri: Uri? = data?.data
                    imageDetectionUpload.setImageURI(imageUri)
                }
            }
        }
    }
}
