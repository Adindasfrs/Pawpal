package com.capstone.pawpal.response

import com.google.gson.annotations.SerializedName

data class DetectionResponse(

	@field:SerializedName("classificationResult")
	val classificationResult: ClassificationResult,

	@field:SerializedName("imageUrl")
	val imageUrl: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int
)

data class Predictions(

	@field:SerializedName("American Shorthair")
	val americanShorthair: Any,

	@field:SerializedName("Turkish Angora")
	val turkishAngora: Any,

	@field:SerializedName("Bengal")
	val bengal: Any,

	@field:SerializedName("Sphynx")
	val sphynx: Any,

	@field:SerializedName("Scottish Fold")
	val scottishFold: Any,

	@field:SerializedName("Persian")
	val persian: Any,

	@field:SerializedName("Siamese")
	val siamese: Any,

	@field:SerializedName("Russian Blue")
	val russianBlue: Any,

	@field:SerializedName("Abyssinian")
	val abyssinian: Any,

	@field:SerializedName("Birman")
	val birman: Any,

	@field:SerializedName("Bombay")
	val bombay: Any,

	@field:SerializedName("Maine Coon")
	val maineCoon: Any,

	@field:SerializedName("British Shorthair")
	val britishShorthair: Any,

	@field:SerializedName("Exotic Shorthair")
	val exoticShorthair: Any,

	@field:SerializedName("Manx")
	val manx: Any,

	@field:SerializedName("Norwegian Forest")
	val norwegianForest: Any,

	@field:SerializedName("American Bobtail")
	val americanBobtail: Any,

	@field:SerializedName("Egyptian Mau")
	val egyptianMau: Any,

	@field:SerializedName("Ragdoll")
	val ragdoll: Any,

	@field:SerializedName("American Curl")
	val americanCurl: Any
)

data class ClassificationResult(

	@field:SerializedName("confidence")
	val confidence: Any,

	@field:SerializedName("cat_breed")
	val catBreed: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("diseases")
	val diseases: String,

	@field:SerializedName("prediction_label")
	val predictionLabel: Int,

	@field:SerializedName("predictions")
	val predictions: Predictions
)
