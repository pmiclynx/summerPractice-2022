package com.summer.practice.tvtracker.data.networking

import okhttp3.Interceptor
import okhttp3.Response

class DetailInterceptor(private val id: Int): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url()
        val url = originalUrl.newBuilder()
            .addPathSegment(id.toString())
            .addQueryParameter("api_key", apiKey)
            .build()

        val requestBuilder = original.newBuilder()
            .url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}