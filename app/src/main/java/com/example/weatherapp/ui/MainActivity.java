package com.example.weatherapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;

import com.example.weatherapp.R;
import com.example.weatherapp.WeatherDetailsActivity;
import com.example.weatherapp.model.Weather;
import com.example.weatherapp.viewmodel.WeatherViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CityReyclerViewAdapter.OnCityClickedListener {
    private WeatherViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.cities_recycler_view);
        final CityReyclerViewAdapter adapter = new CityReyclerViewAdapter(this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel.getAllWeather().observe(this, new Observer<List<Weather>>() {
            @Override
            public void onChanged(List<Weather> weathers) {
                adapter.setWeatherList(weathers);
            }
        });

        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.pullToRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.updateAllCitiesWeather();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        viewModel.scheduleWorkManager();
    }

    @Override
    public void onCityClicked(Weather weather) {
        Intent intent = new Intent(this, WeatherDetailsActivity.class);
        intent.putExtra(WeatherDetailsActivity.EXTRAS_CITY_NAME, weather.getCountry_name());
        intent.putExtra(WeatherDetailsActivity.EXTRAS_TEMPERATURE, weather.getTempCelsius());
        intent.putExtra(WeatherDetailsActivity.EXTRAS_MIN_TEMP, weather.getMinTempCelsius());
        intent.putExtra(WeatherDetailsActivity.EXTRAS_MAX_TEMP, weather.getMaxTempCelsius());
        intent.putExtra(WeatherDetailsActivity.EXTRAS_HUMIDITY, weather.getHumidity());
        intent.putExtra(WeatherDetailsActivity.EXTRAS_PRESSURE, weather.getPressure());
        startActivity(intent);
    }
}
