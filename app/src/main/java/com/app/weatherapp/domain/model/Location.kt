package com.app.weatherapp.domain.model

import android.annotation.SuppressLint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Location(
    val country: String ?= null,
    val lat: Double ?= null,
    val localtime: String ?= null,
    val localtime_epoch: Int ?= null,
    val lon: Double ?= null,
    val name: String ?= null,
    val region: String ?= null,
    val tz_id: String ?= null,
) {
    @SuppressLint("NewApi")
    fun getFormattedTime(): String {
        return try {
             LocalDateTime.parse(localtime , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                .format(DateTimeFormatter.ofPattern("MMM.dd yyyy EEE hh:mm a"))
        } catch (e: Exception) {
           localtime ?: ""
        }
    }
}