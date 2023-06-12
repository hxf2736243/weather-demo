package com.example.gpsdemo.db;

import android.content.Context;

import androidx.room.Room;

import com.example.gpsdemo.data.Weather;

import java.util.List;

public class WeatherDatabase {
    private static WeatherDatabase instance;
    private WeatherDao dao;

    private WeatherDatabase(Context context) {
        AppDatabase db = Room.databaseBuilder(context,
                        AppDatabase.class, "weather_db")
                .build();
        dao = db.weatherDao();
    }

    public static WeatherDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new WeatherDatabase(context);
        }
        return instance;
    }

    public List<Weather> getAllWeathers() {
        return dao.getAllWeathers();
    }

    public void insertWeathers(List<Weather> weathers) {
        dao.insertWeathers(weathers);
    }
}
