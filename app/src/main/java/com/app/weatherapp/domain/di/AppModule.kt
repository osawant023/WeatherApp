package com.app.weatherapp.domain.di

import android.content.Context
import com.app.weatherapp.data.local_storage.AppDatabase
import com.app.weatherapp.data.network_client.ApiService
import com.app.weatherapp.data.network_client.NetworkClient
import com.app.weatherapp.domain.repository.RepoWeather
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return androidx.room.Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideRemoteNetworkClient(): ApiService {
        return NetworkClient.apiService
    }

    @Singleton
    @Provides
    fun provideTasksRemoteDataSource(database: AppDatabase, remote: ApiService): RepoWeather {
        return RepoWeather(database, remote)
    }
}