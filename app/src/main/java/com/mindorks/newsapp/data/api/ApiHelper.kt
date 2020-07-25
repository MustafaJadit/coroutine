package com.mindorks.newsapp.data.api

import com.mindorks.newsapp.data.model.News

interface ApiHelper {

    suspend fun getNews(): List<News>

    suspend fun getMoreNews(): List<News>

}