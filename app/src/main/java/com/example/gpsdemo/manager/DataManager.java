package com.example.gpsdemo.manager;

import com.example.gpsdemo.data.Weather;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static DataManager instance;

    private List<Weather> weathers;

    private DataManager() {
        weathers = new ArrayList<>();
        initData();
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    private void initData() {
//        weathers.add(new Weather("北京", "晴", "30°C", "20°C"));
//        weathers.add(new Weather("西安", "阴", "32°C", "26°C"));
//        weathers.add(new Weather("上海", "多云", "29°C", "24°C"));
//        weathers.add(new Weather("广州", "阵雨", "32°C", "26°C"));
    }

    public List<Weather> getCityWeathers() {
        return weathers;
    }
}
