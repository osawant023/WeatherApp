package com.app.weatherapp.data.local_storage

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.weatherapp.data.local_storage.dao.DaoWeather
import com.app.weatherapp.data.local_storage.entities.DbModelWeather

@Database(entities = [DbModelWeather::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getDaoWeather(): DaoWeather

    /*companion object {
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                val instance =
                this.instance = instance
                instance
            }
        }
    }*/
}