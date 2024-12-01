package com.app.weatherapp.data.local_storage.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.app.weatherapp.data.local_storage.entities.DbModelWeather


@Dao
interface DaoWeather{
    @Query("SELECT * FROM records")
    fun getAllWeatherList(): LiveData<List<DbModelWeather>>

    @Query("SELECT * from records where LOWER(cityName) = LOWER(:cityName)")
    fun getWeatherDetails(cityName :String): DbModelWeather

    @Upsert()
    fun insert(word: DbModelWeather)

    @Query("DELETE FROM records")
    fun deleteAll()

    @Query("SELECT COUNT(*) From records where LOWER(cityName) = LOWER(:cityName)")
    fun isExistCount(cityName: String): Int

    @Query("DELETE FROM records where LOWER(cityName) = LOWER(:cityName)")
    fun deleteByCityName(cityName :Int)

}
