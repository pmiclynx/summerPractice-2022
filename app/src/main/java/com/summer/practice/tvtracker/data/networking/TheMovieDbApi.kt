package com.summer.practice.tvtracker.data.networking

import com.summer.practice.tvtracker.domain.Detail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDbApi {
    @GET("tv")
    fun getList(@Query("page") page: Int = 1) : Call<Results>

    @GET("tv")
    fun getDetail() : Call<Detail>
}