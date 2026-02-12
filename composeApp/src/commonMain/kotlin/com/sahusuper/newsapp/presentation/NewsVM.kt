package com.sahusuper.newsapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sahusuper.newsapp.data.modal.NewsRes
import com.sahusuper.newsapp.data.repo.NewsRepo
import com.sahusuper.newsapp.state.UiState
import com.sahusuper.newsapp.utills.AppLogger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.onSuccess

class NewsVM(private val repo : NewsRepo): ViewModel() {


    private val _type = MutableStateFlow("latest")
    private val _query = MutableStateFlow("")
    val type = _type.asStateFlow()
    val query = _query.asStateFlow()


    fun setType(t: String) { _type.value = t }
    fun onQueryChange(q: String) { _query.value = q }

    val newsState: StateFlow<UiState<NewsRes>> =
        combine(_type, _query){ type, query -> type to query }
            .debounce(400)
            .distinctUntilChanged()
            .flatMapLatest { (type, query) ->
                repo.getNews1(type, query = null)
                    .map<NewsRes, UiState<NewsRes>> {
                        UiState.Success(it)
                    }
                    .onStart {
                        emit(UiState.Loading)
                    }
                    .catch {
                        emit(UiState.Error(it.toString()))
                    }

            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = UiState.Loading
            )



    private val _getNews = MutableStateFlow<UiState<NewsRes>>(UiState.Loading)
    val getNews = _getNews.asStateFlow()

    private val _getNews1 = MutableStateFlow<UiState<NewsRes>>(UiState.Loading)
    val getNews1 = _getNews1.asStateFlow()


    fun getNews1(type: String, query: String?) {
        _getNews1.value = UiState.Loading
        AppLogger.d("NewsVM", "getNews called")

        viewModelScope.launch {
            repo.getNews1(type, query).onStart {
                _getNews1.value = UiState.Loading
            }.catch {
                _getNews1.value = UiState.Error(it.toString())
            }.collect{
                _getNews1.value = UiState.Success(it)
            }

        }
    }



        fun getNews(type: String, query: String?, ) {
            _getNews.value = UiState.Loading
            AppLogger.d("NewsVM", "getNews called")

            viewModelScope.launch {
                repo.getNews(type, query).onSuccess {
                    _getNews.value = UiState.Success(it)
                    AppLogger.d("NewsVM", "$it")


                }
                    .onFailure {
                        _getNews.value = UiState.Error(it.toString())
                        AppLogger.d("NewsVM", it.toString())


                    }
            }
        }

    }











