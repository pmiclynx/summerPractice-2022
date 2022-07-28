package com.summer.practice.tvtracker.data

import com.summer.practice.tvtracker.data.networking.*
import com.summer.practice.tvtracker.domain.Detail
import com.summer.practice.tvtracker.domain.Movie
import com.summer.practice.tvtracker.domain.MovieDataSource
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TheMovieDbDataSource : MovieDataSource {

    override fun getMovies(
        category: String,
        onSuccess: (movies: List<Movie>) -> Unit,
        onError: (String) -> Unit
    ) {
        getList(
            api = getMovieDbApi(PathInterceptor(category)),
            onSuccess = onSuccess,
            onError = onError,
        )
    }

    override fun getDetail(
        id: Int,
        onSuccess: (detail: Detail) -> Unit,
        onError: (String) -> Unit
    ) {
        val callback = createCallbackObject<Detail>(onSuccess, onError)
        getMovieDbApi(DetailInterceptor(id)).getDetail().enqueue(callback)
    }

    private fun getList(
        api: TheMovieDbApi,
        page: Int = 1,
        onSuccess: (movies: List<Movie>) -> Unit,
        onError: (String) -> Unit
    ) {
        val callback = createCallbackObject<Results>(
            onSuccess = { onSuccess.invoke(it.movies) },
            onError = onError
        )

        api.getList(page).enqueue(callback)
    }

    private fun <R> createCallbackObject(
        onSuccess: (result: R) -> Unit,
        onError: (String) -> Unit
    ): Callback<R> {
        return object : Callback<R> {
            override fun onResponse(call: Call<R>, response: Response<R>) {
                if (!response.isSuccessful) {
                    onError.invoke("Unsuccessful Api call")
                    return
                }

                response.body()?.let {
                    onSuccess.invoke(it)
                    return
                }

                onError.invoke("Empty response body")
            }

            override fun onFailure(call: Call<R>, t: Throwable) {
                onError.invoke(t.toString())
            }
        }
    }

    private fun getMovieDbApi(interceptor: Interceptor): TheMovieDbApi {
        val okHttpClient = OkHttpClient()
            .newBuilder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()

        return retrofit.create(TheMovieDbApi::class.java)
    }
}