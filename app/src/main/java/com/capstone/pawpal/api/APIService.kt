package com.capstone.pawpal.api

import com.capstone.pawpal.dataclass.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface APIService {
    @POST("register")
    fun registUser(@Body requestRegister: RegisterDataAccount): Call<ResponseDetail>

    @POST("login")
    fun loginUser(@Body requestLogin: LoginDataAccount): Call<ResponseLogin>

    @GET("detection")
    fun getDetection(
        @Header("Authorization") token: String
    ): Call<ResponseStory>

    @Multipart
    @POST("detection")
    fun uploadPicture(
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody,
        @Header("Authorization") token: String
    ): Call<ResponseDetail>
}

