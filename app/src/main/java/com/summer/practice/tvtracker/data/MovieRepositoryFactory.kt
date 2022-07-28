package com.summer.practice.tvtracker.data

import com.summer.practice.tvtracker.domain.MovieRepository

object MovieRepositoryFactory {
    fun createRepository(): MovieRepository {
        return ImplMovieRepository(TheMovieDbDataSource())
    }
}