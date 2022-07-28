package com.summer.practice.tvtracker.domain

import com.google.gson.annotations.SerializedName

data class Detail(
    @SerializedName("name") val name: String = "",
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("overview") val overview: String = "",
    @SerializedName("vote_average") val voteAverage: Number = 0
)
