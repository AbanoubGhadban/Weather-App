package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class WeatherDetailsActivity extends AppCompatActivity {
    public static final String EXTRAS_CITY_NAME = "EXTRAS_CITY_NAME";
    public static final String EXTRAS_TEMPERATURE = "EXTRAS_TEMPERATURE";
    public static final String EXTRAS_PRESSURE = "EXTRAS_PRESSURE";
    public static final String EXTRAS_HUMIDITY = "EXTRAS_HUMIDITY";
    public static final String EXTRAS_MIN_TEMP = "EXTRAS_MIN_TEMP";
    public static final String EXTRAS_MAX_TEMP = "EXTRAS_MAX_TEMP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);

        TextView tvCityName = findViewById(R.id.tv_city_name);
        TextView tvCityTemp = findViewById(R.id.tv_city_temp);
        TextView tvMaxTemp = findViewById(R.id.tv_max_temp);
        TextView tvMinTemp = findViewById(R.id.tv_min_temp);
        TextView tvPressure = findViewById(R.id.tv_pressure);
        TextView tvHumidity = findViewById(R.id.tv_humidity);

        Intent intent = getIntent();
        String cityName = intent.getStringExtra(EXTRAS_CITY_NAME);
        float temp = intent.getFloatExtra(EXTRAS_TEMPERATURE, 0);
        float maxTemp = intent.getFloatExtra(EXTRAS_MAX_TEMP, 0);
        float minTemp = intent.getFloatExtra(EXTRAS_MIN_TEMP, 0);
        float pressure = intent.getFloatExtra(EXTRAS_PRESSURE, 0);
        float humidity = intent.getFloatExtra(EXTRAS_HUMIDITY, 0);

        tvCityName.setText(cityName);
        tvCityTemp.setText(String.format(getString(R.string.temp_details), temp));
        tvMaxTemp.setText(String.format(getString(R.string.max_temp_details), maxTemp));
        tvMinTemp.setText(String.format(getString(R.string.min_temp_details), minTemp));
        tvHumidity.setText(String.format(getString(R.string.humidity_details), humidity));
        tvPressure.setText(String.format(getString(R.string.pressure_details), pressure));
    }
}
