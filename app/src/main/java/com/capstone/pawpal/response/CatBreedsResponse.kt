package com.capstone.pawpal.response

import com.google.gson.annotations.SerializedName

data class CatBreedsResponse(

	@field:SerializedName("CatBreedsResponse")
	val catBreedsResponse: List<CatBreedsResponseItem>
)

data class CatBreedsResponseItem(

	@field:SerializedName("list_penyakit")
	val listPenyakit: String,

	@field:SerializedName("id_ras")
	val idRas: Int,

	@field:SerializedName("Definisi")
	val definisi: String,

	@field:SerializedName("gambar")
	val gambar: String,

	@field:SerializedName("nama_ras")
	val namaRas: String
)
