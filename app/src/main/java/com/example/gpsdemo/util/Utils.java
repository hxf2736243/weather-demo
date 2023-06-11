package com.example.gpsdemo.util;

import static java.lang.Math.acos;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import com.example.gpsdemo.R;

public class Utils {
    public static int getWeatherIcon(String weather) {
        if ("晴".equals(weather)) {
            return R.drawable.ic_sunny;
        } else if ("阴".equals(weather)) {
            return R.drawable.ic_cloudy;
        } else if ("阵雨".equals(weather) || "雨".equals(weather)) {
            return R.drawable.ic_rainy;
        } else if ("雪".equals(weather)) {
            return R.drawable.ic_snowy;
        }
        return R.drawable.ic_sunny;
    }

    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = radians(lat1);
        double radLat2 = radians(lat2);
        double radLng1 = radians(lng1);
        double radLng2 = radians(lng2);

        double distance = sin(radLat1) * sin(radLat2)
                + cos(radLat1) * cos(radLat2) * cos(radLng1 - radLng2);

        if (distance > 1) {
            distance = 1;
        }

        return 6371.01 * acos(distance);
    }

    public static double radians(double deg) {
        return deg * Math.PI / 180.0;
    }
}
