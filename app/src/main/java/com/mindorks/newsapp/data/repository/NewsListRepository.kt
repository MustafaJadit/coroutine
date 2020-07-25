package com.mindorks.newsapp.data.repository

import com.mindorks.newsapp.data.model.News

interface NewsListRepository {

    suspend fun getNews(): List<News>

    suspend fun getMoreNews(): List<News>

}