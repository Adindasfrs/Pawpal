package com.capstone.pawpal.api

import com.capstone.pawpal.dataclass.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface APIService {

    @POST("register")
    fun registerUser(
        @Body requestRegister: RegisterDataAccount
    ): Call<ResponseDetail>

    @POST
    fun loginUser(
        @Url url: String = "https://www.googleapis.com/identitytoolkit/v3/relyingparty/verifyPassword?key=AIzaSyAPNSSma03HNFSjey_FeSLep7bFeB-ci4g",
        @Body requestLogin: LoginDataAccount
    ): Call<ResponseLogin>

    @GET("catbreeds")
    fun getCatBreeds(): Call<List<CatBreed>>

    @GET("symptoms")
    fun getSymptoms(): Call<List<Symptom>>

    @Multipart
    @POST("upload-image")
    fun uploadPicture(
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody
    ): Call<ResponseDetail>
}
