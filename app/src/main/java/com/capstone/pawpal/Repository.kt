package com.capstone.pawpal

import android.util.Log
import com.capstone.pawpal.api.APIService
import com.capstone.pawpal.response.CatBreedsResponse
import kotlinx.coroutines.flow.flow

class Repository private constructor(
    private val apiService: APIService,
    private val pref: UserPreferences
){

//    fun logout() = runBlocking {
//        pref.saveAccessToken("")
//        pref.logout()
//    }

    fun CatBreeds(listPenyakit: String, idRas: Int, definisi: String, gambar: String, namaRas: String) = flow<Result<CatBreedsResponse>> {
        emit(Result.Loading)
        try {
            val token = "Bearer ${pref.getAccessToken()}"
            val json = """
            {
                "listPenyakit": $listPenyakit,
                "idRas": $idRas,
                "definisi": $definisi,
                "gambar": $gambar,
                "namaRas": $namaRas
            }
            """.trimIndent()
//            val requestBody = json.toRequestBody("application/json".toMediaTypeOrNull())
//            val response = apiService.CatBreeds(token, requestBody)
//            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error occurred"))
            Log.e("Error Cat Breeds", e.message ?: "Unknown error occurred")
        }
    }

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            apiService: APIService,
            pref: UserPreferences
        ): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(apiService, pref)
            }.also { instance = it }
    }
}