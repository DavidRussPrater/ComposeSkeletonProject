package com.spatiumapps.composeskeletonproject.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spatiumapps.composeskeletonproject.data.model.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel
    @Inject
    constructor(
        private val repository: MainRepository
    ) : ViewModel() {
        private val _response = MutableStateFlow<MainViewModelState>(MainViewModelState.Empty)
        val response: StateFlow<MainViewModelState> = _response.asStateFlow()

        private fun getNetworkRequest() {
            _response.value = MainViewModelState.Loading
            viewModelScope.launch {
                try {
                    val response = repository.getRestaurants()
                    _response.value = MainViewModelState.Success(response)
                } catch (e: Exception) {
                    _response.value = MainViewModelState.Error(e)
                }
            }
        }

        init {
            getNetworkRequest()
        }
    }

sealed interface MainViewModelState {
    object Empty : MainViewModelState

    object Loading : MainViewModelState

    data class Error(val throwable: Throwable) : MainViewModelState

    data class Success(val restaurants: Response) : MainViewModelState
}
