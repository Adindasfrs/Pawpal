package com.capstone.pawpal.response

import com.google.gson.annotations.SerializedName

data class DetectionErrorResponse(

	@field:SerializedName("error")
	val error: String
)
