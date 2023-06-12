package com.example.gpsdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.gpsdemo.activity.CityWeatherActivity;
import com.example.gpsdemo.adapter.CityWeatherListAdapter;
import com.example.gpsdemo.data.Weather;
import com.example.gpsdemo.manager.DataManager;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private CityWeatherListAdapter adapter;

    private WeatherViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        adapter = new CityWeatherListAdapter(this, DataManager.getInstance().getCityWeathers());
        listView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        // 观察LiveData
        viewModel.getWeathers().observe(this, weathers -> {
            // 数据变化,更新UI
            adapter.updateDataList(weathers);
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Weather weather = adapter.getItem(position);
                Intent intent = new Intent(MainActivity.this, CityWeatherActivity.class);
                intent.putExtra("weather", weather);
                startActivity(intent);
            }
        });
    }


}