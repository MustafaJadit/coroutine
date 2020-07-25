package com.mindorks.newsapp.ui.newslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindorks.newsapp.data.model.News
import com.mindorks.newsapp.data.repository.NewsListRepository
import com.mindorks.newsapp.utils.Resource
import kotlinx.coroutines.launch

class NewsListViewModel(private val newsListRepository: NewsListRepository) : ViewModel() {

    private val newsList = MutableLiveData<Resource<List<News>>>()

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            newsList.postValue(Resource.loading(null))
            try {
                val response = newsListRepository.getNews()
                newsList.postValue(Resource.success(response))
            } catch (e: Exception) {
                newsList.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun getNews(): LiveData<Resource<List<News>>> {
        return newsList
    }

}