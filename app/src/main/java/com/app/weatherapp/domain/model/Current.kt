package com.app.weatherapp.domain.model

data class Current(
    val cloud: String ?= null,
    val condition: Condition ?= null,
    val dewpoint_c: String ?= null,
    val dewpoint_f: String ?= null,
    val feelslike_c: String ?= null,
    val feelslike_f: String ?= null,
    val gust_kph: String ?= null,
    val gust_mph: String ?= null,
    val heatindex_c: String ?= null,
    val heatindex_f: String ?= null,
    val humidity: String ?= null,
    val is_day: Int ?= null,
    val last_updated: String ?= null,
    val last_updated_epoch: String ?= null,
    val precip_in: String ?= null,
    val precip_mm: String ?= null,
    val pressure_in: String ?= null,
    val pressure_mb: String ?= null,
    val temp_c: String ?= null,
    val temp_f: String ?= null,
    val uv: String ?= null,
    val vis_km: String ?= null,
    val vis_miles: String ?= null,
    val wind_degree: String ?= null,
    val wind_dir: String ?= null,
    val wind_kph: String ?= null,
    val wind_mph: String ?= null,
    val windchill_c: String ?= null,
    val windchill_f: String ?= null
)

