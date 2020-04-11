package com.example.weatherapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.weatherapp.api.WeatherDto;

@Entity
public class Weather {
    @PrimaryKey
    @NonNull
    private String country_name;
    private float temp;
    private float minTemp;
    private float maxTemp;
    private float humidity;
    private float pressure;

    static Weather fromDTO(WeatherDto dto, String cityName) {
        Weather weather = new Weather();
        weather.country_name = cityName;
        weather.temp = dto.getTemp();
        weather.minTemp = dto.getMinTemp();
        weather.maxTemp = dto.getMaxTemp();
        weather.humidity = dto.getHumidity();
        weather.pressure = dto.getPressure();
        return weather;
    }

    private float fromKelvinToCelsius(float k) { return (float) (k - 273.13); }

    @NonNull
    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(@NonNull String country_name) {
        this.country_name = country_name;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(float min_temp) {
        this.minTemp = min_temp;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(float max_temp) {
        this.maxTemp = max_temp;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getTempCelsius() { return fromKelvinToCelsius(this.temp); }

    public float getMaxTempCelsius() { return fromKelvinToCelsius(this.maxTemp); }

    public float getMinTempCelsius() { return fromKelvinToCelsius(this.minTemp); }
}
