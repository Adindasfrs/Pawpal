package com.capstone.pawpal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.pawpal.api.APIConfig
import com.capstone.pawpal.dataclass.LoginDataAccount
import com.capstone.pawpal.dataclass.RegisterDataAccount
import com.capstone.pawpal.dataclass.ResponseDetail
import com.capstone.pawpal.dataclass.ResponseLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log

class MainViewModel : ViewModel() {
    private val _isLoadingLogin = MutableLiveData<Boolean>()
    val isLoadingLogin: LiveData<Boolean> = _isLoadingLogin
    var isErrorLogin: Boolean = false
    private val _messageLogin = MutableLiveData<String>()
    val messageLogin: LiveData<String> = _messageLogin
    private val _userLogin = MutableLiveData<ResponseLogin>()
    val userLogin: LiveData<ResponseLogin> = _userLogin

    var isErrorRegist: Boolean = false
    private val _isLoadingRegist = MutableLiveData<Boolean>()
    val isLoadingRegist: LiveData<Boolean> = _isLoadingRegist
    private val _messageRegist = MutableLiveData<String>()
    val messageRegist: LiveData<String> = _messageRegist

    fun getResponseLogin(loginDataAccount: LoginDataAccount) {
        _isLoadingLogin.value = true
        val api = APIConfig.getApiService().loginUser(loginDataAccount)
        api.enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                _isLoadingLogin.value = false

                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        isErrorLogin = false
                        _messageLogin.value = "Halo Login Berhasil!"
                    } else {
                        isErrorLogin = true
                        _messageLogin.value = "Response body is null"
                        Log.e("LoginError", "Response body is null")
                    }
                } else {
                    isErrorLogin = true
                    when (response.code()) {
                        401 -> _messageLogin.value = "Email atau password yang anda masukan salah, silahkan coba lagi"
                        408 -> _messageLogin.value = "Koneksi internet anda lambat, silahkan coba lagi"
                        else -> {
                            _messageLogin.value = "Pesan error: " + response.message()
                            Log.e("LoginError", "Error code: ${response.code()}, message: ${response.message()}, errorBody: ${response.errorBody()?.string()}")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                isErrorLogin = true
                _isLoadingLogin.value = false
                _messageLogin.value = "Pesan error: " + t.message.toString()
                Log.e("LoginError", "onFailure: ${t.message}", t)
            }
        })
    }

    fun getResponseRegister(registDataUser: RegisterDataAccount) {
        _isLoadingRegist.value = true
        val api = APIConfig.getApiService().registUser(registDataUser)
        api.enqueue(object : Callback<ResponseDetail> {
            override fun onResponse(call: Call<ResponseDetail>, response: Response<ResponseDetail>) {
                _isLoadingRegist.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        isErrorRegist = false
                        _messageRegist.value = "Yeay akun berhasil dibuat"
                    } else {
                        isErrorRegist = true
                        _messageRegist.value = "Response body is null"
                        Log.e("RegisterError", "Response body is null")
                    }
                } else {
                    isErrorRegist = true
                    when (response.code()) {
                        400 -> _messageRegist.value = "Request tidak valid"
                        408 -> _messageRegist.value = "Koneksi internet anda lambat, silahkan coba lagi"
                        else -> {
                            _messageRegist.value = "Pesan error: " + response.message()
                            Log.e("RegisterError", "Error code: ${response.code()}, message: ${response.message()}, errorBody: ${response.errorBody()?.string()}")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDetail>, t: Throwable) {
                isErrorRegist = true
                _isLoadingRegist.value = false
                _messageRegist.value = "Pesan error: " + t.message.toString()
                Log.e("RegisterError", "onFailure: ${t.message}", t)
            }
        })
    }
}
