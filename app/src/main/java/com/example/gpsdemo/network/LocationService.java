package com.example.gpsdemo.network;

import android.util.Log;

import androidx.annotation.NonNull;
import com.example.gpsdemo.data.City;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LocationService {
    private static final String LOCATION_URL = "https://restapi.amap.com/v3/geocode/regeo?location={location}&key=9415e97066d0ef87372c84d112b22bde";

    public void getLocationAsync(String location, LocationCallback callback) {
        OkHttpClient client = new OkHttpClient();
        try {
            Request request = new Request.Builder().url(LOCATION_URL.replace("{location}",location)).build();
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
                    Log.d("okHttp", "getLocationAsync->"+location);
                    // 获取 body 数据
                    String body = response.body().string();
                    // JSON 解析
                    try {
                        JSONObject jsonObj = new JSONObject(body);
                        JSONObject args = jsonObj.getJSONObject("regeocode");
                        JSONObject addressComponent = args.getJSONObject("addressComponent");
                        String adcode = addressComponent.getString("adcode");
                        String district = addressComponent.getString("district");
                        String city = addressComponent.getString("city");
                        Log.d("okHttp", district);
                        //在主线程中更新TextView
                        City city1=new City();
                        city1.setCityId(adcode);
                        city1.setName(city);
                        callback.onSuccess(city1);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public interface LocationCallback {
        void onSuccess(City city);
        void onFailure(String error);
    }
}
