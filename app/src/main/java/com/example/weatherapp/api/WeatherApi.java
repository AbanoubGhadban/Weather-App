package com.example.weatherapp.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("weather?")
    Call<WeatherDto> getCityWeather(@Query("q") String cityName);
}
