package com.app.weatherapp.data.local_storage.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.weatherapp.domain.model.Condition
import com.app.weatherapp.domain.model.Current
import com.app.weatherapp.domain.model.DtoWeatherResponseModel
import com.app.weatherapp.domain.model.Location

@Entity(tableName = "records")
data class DbModelWeather(
    @PrimaryKey
    val cityName: String,
    val cloud: String,
    val condition: String,
    val humidity: String,
    val is_day: Int,
    val temp_c: String,
    val temp_f: String,
    val uv: String,
    val date: String,
) {
    fun toWeatherDataResponse(): DtoWeatherResponseModel {
        return DtoWeatherResponseModel(
            current = Current(
                cloud = this.cloud,
                condition = Condition(code = 0, text = this.condition, icon = ""),
                humidity = this.humidity,
                is_day = this.is_day,
                temp_c = this.temp_c,
                temp_f = this.temp_f,
                uv = this.uv
            ),
            location = Location(name = cityName, localtime = date)
        )
    }

}
