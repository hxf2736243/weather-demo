package com.example.gpsdemo.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gpsdemo.R;
import com.example.gpsdemo.data.Weather;

public class CityWeatherActivity extends AppCompatActivity {
    private TextView cityName;
    private TextView weatherInfo;
    private TextView tempHighest;
    private TextView tempLowest;
    private TextView updateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_weather);

        cityName = findViewById(R.id.cityName);
        weatherInfo = findViewById(R.id.weatherInfo);
        tempHighest = findViewById(R.id.tempHighest);
        tempLowest = findViewById(R.id.tempLowest);
        updateTime = findViewById(R.id.updateTime);

        Weather weather = (Weather) getIntent().getSerializableExtra("weather");
        showWeather(weather);
    }

    private void showWeather(Weather weather) {
        cityName.setText(weather.getCity());
        weatherInfo.setText(weather.getWeather());
        tempHighest.setText(weather.getTempMax() + "°C");
        tempLowest.setText(weather.getTempMin() + "°C");
        updateTime.setText("更新时间:" );
    }
}
