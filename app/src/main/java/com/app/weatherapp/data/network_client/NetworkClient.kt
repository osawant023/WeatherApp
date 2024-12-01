package com.app.weatherapp.data.network_client

import com.app.weatherapp.domain.model.DtoWeatherResponseModel
import com.app.weatherapp.domain.utill.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url
import java.util.concurrent.TimeUnit

interface ApiService {
    @GET
    suspend fun getWeatherDetails(@Url url: String): Response<DtoWeatherResponseModel>
}

object Webservice {
    private const val WS_GET_WEATHER_DETAILS = "current.json?"

    fun getWeatherDetailsUrl(query: String = "Mumbai"): String {
        return buildString {
            append(WS_GET_WEATHER_DETAILS)
            append("key=${Constants.WEATHER_API_KEY}")
            append("&q=$query")
        }
    }
}

object NetworkClient {

    private const val BASE_URL = "http://api.weatherapi.com/v1/"
    private val logger by lazy {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .readTimeout(5L, TimeUnit.SECONDS)
                    .connectTimeout(5L, TimeUnit.SECONDS)
                    .writeTimeout(5L, TimeUnit.SECONDS)
                    .build()
            )
            .build()
            .create(ApiService::class.java)
    }
}