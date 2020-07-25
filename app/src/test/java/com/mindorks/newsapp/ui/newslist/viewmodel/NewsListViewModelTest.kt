package com.mindorks.newsapp.ui.newslist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mindorks.newsapp.data.model.News
import com.mindorks.newsapp.data.repository.NewsListRepository
import com.mindorks.newsapp.utils.Resource
import com.mindorks.newsapp.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NewsListViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var newsListRepository: NewsListRepository

    @Mock
    private lateinit var newsListObserver: Observer<Resource<List<News>>>

    @Before
    fun setUp() {
        // do something if required
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            doReturn(emptyList<News>())
                .`when`(newsListRepository)
                .getNews()
            val viewModel = NewsListViewModel(newsListRepository)
            viewModel.getNews().observeForever(newsListObserver)
            verify(newsListRepository).getNews()
            verify(newsListObserver).onChanged(Resource.success(emptyList()))
            viewModel.getNews().removeObserver(newsListObserver)
        }
    }

    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error Message For You"
            doThrow(RuntimeException(errorMessage))
                .`when`(newsListRepository)
                .getNews()
            val viewModel = NewsListViewModel(newsListRepository)
            viewModel.getNews().observeForever(newsListObserver)
            verify(newsListRepository).getNews()
            verify(newsListObserver).onChanged(
                Resource.error(
                    RuntimeException(errorMessage).toString(),
                    null
                )
            )
            viewModel.getNews().removeObserver(newsListObserver)
        }
    }

    @After
    fun tearDown() {
        // do something if required
    }

}