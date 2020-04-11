package com.example.weatherapp.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Weather.class}, version = 1)
public abstract class WeatherDB extends RoomDatabase {
    private static WeatherDB INSTANCE;

    public abstract WeatherDao getDao();

    public static WeatherDB getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (WeatherDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                                WeatherDB.class, "weather_db")
                            .fallbackToDestructiveMigration()
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
