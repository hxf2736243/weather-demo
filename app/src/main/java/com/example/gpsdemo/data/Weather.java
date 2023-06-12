package com.example.gpsdemo.data;


import java.io.Serializable;

public class Weather  implements Serializable {

    public Weather(String city, String weather, String tempMax, String tempMin) {
        this.city = city;
        this.weather = weather;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
    }

    public Weather() {
    }

    private String city;
    private String cityId;
    private String date;
    private String weather;
    private String aqi;    // 空气质量指数
    private String tempLive;
    private String tempMax;
    private String tempMin;
    private String wind;       // 风力,如3-4级等

    public String getTempLive() {
        return tempLive;
    }

    public void setTempLive(String tempLive) {
        this.tempLive = tempLive;
    }

    private String windDirect; // 风向,如东南风等
    private String sunrise;
    private String sunset; // 日落时间

    private Forecast[] forecasts;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getWindDirect() {
        return windDirect;
    }

    public void setWindDirect(String windDirect) {
        this.windDirect = windDirect;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public Forecast[] getForecasts() {
        return forecasts;
    }

    public void setForecasts(Forecast[] forecasts) {
        this.forecasts = forecasts;
    }
}
