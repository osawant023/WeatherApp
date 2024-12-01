package com.app.weatherapp.domain.repository


import com.app.weatherapp.data.local_storage.AppDatabase
import com.app.weatherapp.data.network_client.ApiService
import com.app.weatherapp.data.network_client.Webservice
import com.app.weatherapp.domain.utill.NetworkUtil
import com.app.weatherapp.domain.utill.ScreenState


class RepoWeather(
    private val networkUtil: NetworkUtil,
    private val db: AppDatabase,
    private val remote: ApiService,
) {

    suspend fun getWeatherDetails(
        query: String,
        fetchUpdatedData: Boolean,
        onResult: suspend (ScreenState) -> Unit,
    ) {
        try {
            val weatherDao = db.getDaoWeather()
            val isRecordExist = weatherDao.isExistCount(query) > 0
            if (!fetchUpdatedData && isRecordExist) {
                val response = weatherDao.getWeatherDetails(query)
                onResult.invoke(ScreenState.Success(response.toWeatherDataResponse()))
                return
            }

            if (!networkUtil.getConnectivityStatus()) {
                onResult.invoke(ScreenState.Error("No Internet Connection"))
                return
            }

            onResult.invoke(ScreenState.Loading)
            val response = remote.getWeatherDetails(Webservice.getWeatherDetailsUrl(query))
            if (response.isSuccessful) {
                response.body()?.let {
                    weatherDao.insert(it.toDbModelWeather())
                }
                onResult.invoke(ScreenState.Success(response.body()))
            } else {
                onResult.invoke(ScreenState.Error(response.errorBody()?.string() ?: "Some thing went wrong ${response.code()}"))
            }
        } catch (e: Exception) {
            onResult.invoke(ScreenState.Error(e.localizedMessage ?: ""))
        }
    }
}