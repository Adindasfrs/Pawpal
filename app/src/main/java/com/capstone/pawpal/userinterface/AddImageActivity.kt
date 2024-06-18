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
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.capstone.pawpal.R

class AddImageActivity : AppCompatActivity() {

    private lateinit var ivBackArrow: ImageView
    private lateinit var imageDetectionUpload: ImageView
    private lateinit var cameraButton: Button
    private lateinit var galleryButton: Button
    private lateinit var analyzeButton: Button
    private lateinit var progressBar: ProgressBar

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

        ivBackArrow.setOnClickListener { finish() }
        cameraButton.setOnClickListener { openCamera() }
        galleryButton.setOnClickListener { openGallery() }
        analyzeButton.setOnClickListener { analyzeImage() }
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
        // Example: Run analysis and then hide the progress bar
        // progressBar.visibility = ProgressBar.GONE
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
