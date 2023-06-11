package com.example.gpsdemo;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.gpsdemo.adapter.CityWeatherListAdapter;
import com.example.gpsdemo.data.City;
import com.example.gpsdemo.data.Weather;
import com.example.gpsdemo.network.LocationService;
import com.example.gpsdemo.network.WeatherService;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_LOCATION = 1;
    LocationManager locationManager;
    LocationListener locationListener;
    private ListView listView;
    private CityWeatherListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 获取 WebView 组件
        WebView webView = findViewById(R.id.weather_wv);
        // 允许 JavaScript
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        // 加载 assets 文件夹下的 test.html 文件
        webView.loadUrl("file:///android_asset/weather.html");

        Button btnSwitchCity = findViewById(R.id.btn_switch_city);
        btnSwitchCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Weather","Button pressed");
                // 切换城市代码
                getWeatherAsync();

            }
        });

        // 获取位置管理器
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // 定义位置监听器
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                // 位置变化时回调,更新UI
                showLocation(location);
            }
        };

        // 请求位置更新
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    "android.permission.ACCESS_FINE_LOCATION")) {
                Toast.makeText(this, "需要定位权限", Toast.LENGTH_SHORT).show();
            }
            // 请求定位权限
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_FINE_LOCATION"},REQUEST_CODE_LOCATION);
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);
    }

    private void showLocation(Location location) {
        Log.d("GMS","lat:"+location.getLatitude()+",long:"+location.getLongitude());
        // 更新UI,显示位置信息
//        getLocationAsync(Math.abs(location.getLongitude())+","+Math.abs(location.getLatitude()));
    }

    private void getLocationAsync(String location) {
        LocationService locationService=new LocationService();
        locationService.getLocationAsync(location, new LocationService.LocationCallback() {
            @Override
            public void onSuccess(City city) {
                //在主线程中更新TextView
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    }
                });
            }

            @Override
            public void onFailure(String error) {
            }
        });

    }
    private void getWeatherAsync() {

        WeatherService service = new WeatherService();
        service.getWeather("110105", new WeatherService.WeatherCallback() {
            @Override
            public void onSuccess(Weather weather) {
                // 更新 UI
                //在主线程中更新TextView
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    }
                });
                Log.d("getWeatherAsync",weather.getWeather());
            }

            @Override
            public void onFailure(String error) {
                // 显示错误
            }
        });
    }
}