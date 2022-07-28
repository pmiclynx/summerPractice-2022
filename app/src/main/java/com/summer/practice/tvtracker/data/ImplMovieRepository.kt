package com.summer.practice.tvtracker.data

import com.summer.practice.tvtracker.data.networking.imageBaseUrl
import com.summer.practice.tvtracker.domain.Detail
import com.summer.practice.tvtracker.domain.Movie
import com.summer.practice.tvtracker.domain.MovieDataSource
import com.summer.practice.tvtracker.domain.MovieRepository

class ImplMovieRepository(private val movieDataSource: MovieDataSource) : MovieRepository {

    override fun getMovies(
        category: String,
        onSuccess: (movies: List<Movie>) -> Unit,
        onError: (String) -> Unit
    ) {
        movieDataSource.getMovies(
            category = category,
            onSuccess = { onSuccess.invoke(createPathList(it)) },
            onError = onError
        )
    }

    override fun getDetail(
        id: Int,
        onSuccess: (detail: Detail) -> Unit,
        onError: (String) -> Unit
    ) {
        movieDataSource.getDetail(id, onSuccess, onError)
    }

    private fun createPathList(movies: List<Movie>): List<Movie> {
        val pathList = movies.map {
            var newPath: String? = null
            it.backdropPath?.let {
                newPath = imageBaseUrl + it
            }
            it.copy(backdropPath = newPath)
        }
        return pathList
    }
}
