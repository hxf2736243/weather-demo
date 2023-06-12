package com.example.gpsdemo.db;

import androidx.room.Insert;
import androidx.room.Query;

import com.example.gpsdemo.data.Weather;

import java.util.List;

public interface WeatherDao {
    @Query("SELECT * FROM weather")
    List<Weather> getAllWeathers();

    @Insert
    void insertWeathers(List<Weather> weathers);
}
