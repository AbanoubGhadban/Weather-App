package com.example.weatherapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.weatherapp.api.WeatherWorker;
import com.example.weatherapp.model.Weather;
import com.example.weatherapp.model.WeatherRpository;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class WeatherViewModel extends AndroidViewModel {
    private WeatherRpository weatherRpository;
    private LiveData<List<Weather>> allWeather;
    private WorkManager workManager;

    public WeatherViewModel(@NonNull Application application) {
        super(application);
        weatherRpository = new WeatherRpository(application);
        allWeather = weatherRpository.getAllWeather();
        workManager = WorkManager.getInstance(application);
    }

    public LiveData<List<Weather>> getAllWeather() {
        return allWeather;
    }

    public void scheduleWorkManager() {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        PeriodicWorkRequest request = new PeriodicWorkRequest.Builder(WeatherWorker.class, 1, TimeUnit.HOURS)
                .setConstraints(constraints)
                .build();
        workManager.enqueue(request);
    }

    public void updateAllCitiesWeather() {
        weatherRpository.updateAllCitiesWeather();
    }
}
