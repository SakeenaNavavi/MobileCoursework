package com.example.coursework

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("everything")
    fun getsuperHeroes(
        @Query("apiKey") key: String?,  //  @Query( "country" ) String country,
        // @Query( "category" ) String category,
        @Query("pageSize") maxResults: Int,
        @Query("q") q: String? // @Query( "sortBy" ) String sortBy
        // @Query( "sources" ) String sources
    ): Call<NewsResults?>?

    companion object {
        const val BASE_URL: String = "https://newsapi.org/v2/"
    }
}