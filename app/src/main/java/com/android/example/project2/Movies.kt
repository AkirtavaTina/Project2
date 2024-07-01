package com.android.example.project2

import com.google.gson.annotations.SerializedName

data class Movies(
     @SerializedName("poster_path") val path: String,
     @SerializedName("original_name") val name: String,
     @SerializedName("overview") val overview: String,
     @SerializedName("vote_average") val vote: Float
)
