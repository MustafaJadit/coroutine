package com.mindorks.newsapp.data.repository

import com.mindorks.newsapp.data.api.ApiHelper

class NewsListRepositoryImpl(private val apiHelper: ApiHelper) : NewsListRepository {

    override suspend fun getNews() = apiHelper.getNews()

    override suspend fun getMoreNews() = apiHelper.getMoreNews()

}