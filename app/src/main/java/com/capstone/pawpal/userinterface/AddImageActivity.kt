package com.capstone.pawpal.userinterface

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.capstone.pawpal.R
import com.capstone.pawpal.UserPreferences
import com.capstone.pawpal.databinding.ActivityAddImageBinding
import com.capstone.pawpal.viewmodel.AddImageViewModel
import com.capstone.pawpal.viewmodel.UserLoginViewModel
import com.capstone.pawpal.viewmodel.ViewModelFactory
import id.zelory.compressor.Compressor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*

class AddImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddImageBinding
    private lateinit var token: String

    private var getFile: File? = null
    private lateinit var fileFinal: File

    private val addImageViewModel: AddImageViewModel by lazy {
        ViewModelProvider(this)[AddImageViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = resources.getString(R.string.post_users)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        ifClicked()

        val preferences = UserPreferences.getInstance(dataStore)
        val userLoginViewModel =
            ViewModelProvider(this, ViewModelFactory(preferences))[UserLoginViewModel::class.java]

        userLoginViewModel.getToken().observe(this) {
            token = it
        }

        userLoginViewModel.getName().observe(this) {
            binding.tvUsers.text = StringBuilder(getString(R.string.post_as)).append(" ").append(it)
        }

        addImageViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun ifClicked() {
        binding.analyzeButton.setOnClickListener {
            lifecycleScope.launch {
                getFile?.let { file ->
                    fileFinal = compressImageFile(file)

                    val analyzedResult = "Hasil analisis Anda"
                    val imageUri = "content://path/to/your/image" // Ganti dengan URI sesuai dengan gambar yang dianalisis

                    val intent = Intent(this@AddImageActivity, AnalyzeResultActivity::class.java)
                    intent.putExtra("analyzedResult", analyzedResult)
                    intent.putExtra("imageUri", imageUri)
                    startActivity(intent)
                    val requestImageFile = fileFinal.asRequestBody("image/jpeg".toMediaTypeOrNull())
                    val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                        "photo",
                        fileFinal.name,
                        requestImageFile
                    )

                    // Use the imageMultipart to upload the file to the server
                }
            }
        }

        binding.cameraButton.setOnClickListener {
            startTakePhoto()
        }

        binding.galleryButton.setOnClickListener {
            startGallery()
        }
    }

    private suspend fun compressImageFile(file: File): File {
        var compressedFile: File? = null
        var compressedFileSize = file.length()

        return withContext(Dispatchers.IO) {
            while (compressedFileSize > 1 * 1024 * 1024) {
                compressedFile = Compressor.compress(applicationContext, file)
                compressedFileSize = compressedFile?.length() ?: file.length()
            }
            compressedFile ?: file
        }
    }

    private var anyPhoto = false
    private lateinit var currentPhotoPath: String
    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val myFile = File(currentPhotoPath)
            getFile = myFile
            val result = BitmapFactory.decodeFile(myFile.path)
            anyPhoto = true
            binding.imageStoryUpload.setImageBitmap(result)
        }
    }

    private val timeStamp: String = SimpleDateFormat(
        FILENAME_FORMAT,
        Locale.US
    ).format(System.currentTimeMillis())

    private fun createCustomTempFile(context: Context): File {
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(timeStamp, ".jpg", storageDir)
    }

    private fun startTakePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(packageManager)
        createCustomTempFile(application).also {
            val photoURI: Uri = FileProvider.getUriForFile(
                this@AddImageActivity,
                getString(R.string.package_name),
                it
            )
            currentPhotoPath = it.absolutePath
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            launcherIntentCamera.launch(intent)
        }
    }

    private fun uriToFile(selectedImg: Uri, context: Context): File {
        val contentResolver: ContentResolver = context.contentResolver
        val myFile = createCustomTempFile(context)

        val inputStream = contentResolver.openInputStream(selectedImg) as InputStream
        val outputStream: OutputStream = FileOutputStream(myFile)
        val buf = ByteArray(1024)
        var len: Int
        while (inputStream.read(buf).also { len = it } > 0) outputStream.write(buf, 0, len)
        outputStream.close()
        inputStream.close()

        return myFile
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri
            val myFile = uriToFile(selectedImg, this@AddImageActivity)
            getFile = myFile
            binding.imageStoryUpload.setImageURI(selectedImg)
        }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, getString(R.string.choose_picture))
        launcherIntentGallery.launch(chooser)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        finish()
        return super.onSupportNavigateUp()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBarAddStory.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val FILENAME_FORMAT = "MMddyyyy"
    }
}
