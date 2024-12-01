package com.app.weatherapp.domain.utill

sealed class ScreenState {
    data object Loading : ScreenState()
    data class Error(val message: String) : ScreenState()
    data class Success<T>(val data: T) : ScreenState()
}