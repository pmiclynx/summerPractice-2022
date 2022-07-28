package com.summer.practice.tvtracker.domain

interface MovieDataSource {
    fun getMovies(
        category: String,
        onSuccess: (movies: List<Movie>) -> Unit,
        onError: (String) -> Unit
    )

    fun getDetail(
        id: Int,
        onSuccess: (detail: Detail) -> Unit,
        onError: (String) -> Unit
    )
}