package com.mindorks.newsapp.data.api

import com.mindorks.newsapp.data.model.News
import retrofit2.http.GET

interface ApiService {

    @GET("news")
    suspend fun getNews(): List<News>

    @GET("more-news")
    suspend fun getMoreNews(): List<News>

}