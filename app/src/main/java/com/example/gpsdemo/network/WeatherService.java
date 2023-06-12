package com.example.gpsdemo.network;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.gpsdemo.data.Weather;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherService {

    private static final String WEATHER_URL = "https://restapi.amap.com/v3/weather/weatherInfo?city={cityId}&key=9415e97066d0ef87372c84d112b22bde";

    public Weather getWeather(String cityId) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(WEATHER_URL.replace("{cityId}",cityId)).build();
        // JSON 解析
        try {
            Response response = client.newCall(request).execute();
            String body = response.body().string();
            JSONObject jsonObj = new JSONObject(body);
            JSONArray args = jsonObj.getJSONArray("lives");
            JSONObject first=args.getJSONObject(0);
            String city = first.getString("city");
            String adcode = first.getString("adcode");
            String weather = first.getString("weather");
            String temperature = first.getString("temperature");
            String windpower = first.getString("windpower");
            String winddirection = first.getString("winddirection");
            Weather weather1=new Weather();
            weather1.setWeather(weather);
            weather1.setCity(city);
            weather1.setTempLive(temperature);
            weather1.setWind(windpower);
            weather1.setWindDirect(winddirection);
            weather1.setTempMax(temperature);
            return weather1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    public void getWeather(String cityId, final WeatherCallback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(WEATHER_URL.replace("{cityId}",cityId)).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("okHttp", e.getMessage());
                callback.onFailure(e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                // 请求成功调用
                Log.d("okHttp", "onResponse");
                // 获取 body 数据
                String body = response.body().string();
                // JSON 解析
                try {
                    JSONObject jsonObj = new JSONObject(body);
                    JSONArray args = jsonObj.getJSONArray("lives");
                    JSONObject first=args.getJSONObject(0);
                    String city = first.getString("city");
                    String adcode = first.getString("adcode");
                    String weather = first.getString("weather");
                    String temperature = first.getString("temperature");
                    String windpower = first.getString("windpower");
                    String winddirection = first.getString("winddirection");
                    Weather weather1=new Weather();
                    weather1.setWeather(weather);
                    weather1.setCity(city);
                    weather1.setTempLive(temperature);
                    weather1.setWind(windpower);
                    weather1.setWindDirect(winddirection);
                    weather1.setTempMax(temperature);
                    callback.onSuccess(weather1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static interface WeatherCallback {
        void onSuccess(Weather weather);
        void onFailure(String error);
    }
}
