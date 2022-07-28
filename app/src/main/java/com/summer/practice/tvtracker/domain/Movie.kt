package com.summer.practice.tvtracker.domain

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("backdrop_path") val backdropPath: String? = null,
)
