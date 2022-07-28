package com.summer.practice.tvtracker.data.networking

import com.summer.practice.tvtracker.domain.Detail
import com.summer.practice.tvtracker.domain.Movie

fun createPathList(detail: Detail): Detail {
    var newBackdropPath: String? = null
    var newPosterPath: String? = null

    detail.backdropPath?.let{
        newBackdropPath = imageBaseUrl + detail.backdropPath
    }

    detail.posterPath?.let{
        newPosterPath = imageBaseUrl + detail.posterPath
    }

    return detail.copy(
        backdropPath = newBackdropPath,
        posterPath = newPosterPath
    )
}