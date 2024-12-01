package com.app.weatherapp.domain.model

import com.app.weatherapp.data.local_storage.entities.DbModelWeather

data class DtoWeatherResponseModel(
    val current: Current? = null,
    val location: Location? = null,
    val error: Error? = null,
){
    fun toDbModelWeather(): DbModelWeather {
        return DbModelWeather(
            cityName = location?.name ?: "",
            cloud = current?.cloud ?: "",
            condition = current?.condition?.text ?: "",
            humidity = current?.humidity ?: "",
            is_day = current?.is_day ?: 0,
            temp_c = current?.temp_c ?: "",
            temp_f = current?.temp_f ?: "",
            uv = current?.uv ?: "",
            date = location?.localtime ?: ""
        )
    }
}


