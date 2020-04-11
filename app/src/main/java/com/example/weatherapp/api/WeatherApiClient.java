package com.example.weatherapp.api;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherApiClient {
    private static String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    private static String APP_ID = "6d3f7dbae05958d6f80ff3ec9d0a2346";
    private static WeatherApi INSTANCE;

    static Retrofit getService() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        HttpUrl httpUrl = original.url();

                        HttpUrl newHttpUrl = httpUrl.newBuilder()
                                .addQueryParameter("appid", APP_ID)
                                .build();

                        Request.Builder requestBuilder = original.newBuilder().url(newHttpUrl);
                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                }).build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public static WeatherApi getWeatherApi() {
        if (INSTANCE == null) {
            synchronized (WeatherApiClient.class) {
                if (INSTANCE == null) {
                    INSTANCE = getService().create(WeatherApi.class);
                }
            }
        }
        return INSTANCE;
    }
}
