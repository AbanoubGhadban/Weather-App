package com.example.weatherapp.api;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.weatherapp.model.WeatherRpository;

public class WeatherWorker extends Worker {
    private WeatherRpository weatherRpository;

    public WeatherWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        weatherRpository = new WeatherRpository(context);
    }

    @NonNull
    @Override
    public Result doWork() {
        weatherRpository.updateAllCitiesWeather();
        return Result.success();
    }
}
