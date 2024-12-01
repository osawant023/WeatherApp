package com.app.weatherapp.presentation.weather_screen.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.weatherapp.domain.model.DtoWeatherResponseModel
import com.app.weatherapp.domain.repository.RepoWeather
import com.app.weatherapp.domain.utill.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repo: RepoWeather,
) : ViewModel() {

    var response: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Loading)
        private set

    var query: String = "Mumbai"
    var fetchUpdatedData: Boolean = false

    fun getWeatherDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getWeatherDetails(query, fetchUpdatedData) {
                response.emit(it)
            }
        }
    }
}