package com.example.weatherapp.model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.weatherapp.api.WeatherApi;
import com.example.weatherapp.api.WeatherApiClient;
import com.example.weatherapp.api.WeatherDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRpository {
    private static final String TAG = "WeatherRpository";
    final static String[] CITIES = {"Cairo,Egypt", "London,uk", "Paris,France"};

    WeatherDao weatherDao;
    WeatherApi api;
    LiveData<List<Weather>> allWeather;

    public WeatherRpository(Context context) {
        api = WeatherApiClient.getWeatherApi();
        weatherDao = WeatherDB.getInstance(context).getDao();
        allWeather = weatherDao.getAllWeather();
    }

    public LiveData<List<Weather>> getAllWeather() {
        return allWeather;
    }

    public void insertWeather(Weather weather) {
        new InsertWeatherAsync(weatherDao).execute(weather);
    }

    public void updateAllCitiesWeather() {
        for (String cityName : CITIES) {
            updateCityWeather(cityName);
        }
    }

    public void updateCityWeather(final String cityName) {
        Log.d(TAG, "updateCityWeather: Updating " + cityName);
        api.getCityWeather(cityName)
                .enqueue(new Callback<WeatherDto>() {
                    @Override
                    public void onResponse(Call<WeatherDto> call, Response<WeatherDto> response) {
                        Log.d(TAG, "onResponse: Got Response " + cityName);
                        WeatherDto body = response.body();
                        if (response.isSuccessful() && response.code() == 200 && body != null) {
                            Weather weather = Weather.fromDTO(body, cityName);
                            insertWeather(weather);
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherDto> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + cityName);
                    }
                });
    }

    private static class InsertWeatherAsync extends AsyncTask<Weather, Void, Void> {
        WeatherDao weatherDao;

        InsertWeatherAsync(WeatherDao weatherDao) {
            this.weatherDao = weatherDao;
        }

        @Override
        protected Void doInBackground(Weather... weathers) {
            weatherDao.insertWeather(weathers[0]);
            return null;
        }
    }
}
