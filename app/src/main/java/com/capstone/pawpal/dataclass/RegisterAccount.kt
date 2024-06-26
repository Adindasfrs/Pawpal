package com.capstone.pawpal.dataclass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class RegisterDataAccount(
    var username: String,
    var email: String,
    var password: String
)

data class LoginDataAccount(
    var email: String,
    var password: String
)

data class ResponseDetail(
    var error: Boolean,
    var message: String
)

data class ResponseLogin(
    var error: Boolean,
    var message: String,
    var loginResult: LoginResult
)

data class LoginResult(
    var token: String
)

data class ResponseStory(
    var error: String,
    var message: String,
    var listStory: List<StoryDetail>
)

@Parcelize
data class StoryDetail(
    var name: String,
    var description: String,
) : Parcelable