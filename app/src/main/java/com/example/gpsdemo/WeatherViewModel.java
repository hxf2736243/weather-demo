package com.example.gpsdemo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gpsdemo.data.Weather;
import com.example.gpsdemo.network.WeatherService;

import java.util.ArrayList;
import java.util.List;

public class WeatherViewModel extends ViewModel {
    private String[] cities={"110000","610100","610500","310000","530100","440100","650100","230100"};
    private MutableLiveData<List<Weather>> weathersLiveData;

    public WeatherViewModel() {
        weathersLiveData = new MutableLiveData<>();
        fetchWeather(); // 异步获取天气数据
    }

    private void fetchWeather() {
        new Thread() {
            @Override
            public void run() {
                WeatherService weatherService=new WeatherService();
                List<Weather> weathers=new ArrayList<>();
                weathers.clear();
                for (String city:cities){
                    Weather weather = weatherService.getWeather(city);
                    weathers.add(weather);
                    Log.d("loadCity",city+",weathers:"+weathers.size());

                }
                Log.d("loadCity","city load finish!"+weathers.size());
                weathersLiveData.postValue(weathers); // 发布天气数据
            }
        }.start();
    }

    public LiveData<List<Weather>> getWeathers() {
        return weathersLiveData;
    }
}
