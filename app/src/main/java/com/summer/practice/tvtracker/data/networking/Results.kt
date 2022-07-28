package com.summer.practice.tvtracker.data.networking

import com.google.gson.annotations.SerializedName
import com.summer.practice.tvtracker.domain.Movie

data class Results(
    @SerializedName("results") var movies: List<Movie> = emptyList(),
)
