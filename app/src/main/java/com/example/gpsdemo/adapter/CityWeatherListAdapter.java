package com.example.gpsdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gpsdemo.R;
import com.example.gpsdemo.data.Weather;
import com.example.gpsdemo.util.Utils;

import java.util.List;

public class CityWeatherListAdapter extends BaseAdapter {
    private Context context;
    private List<Weather> weathers;

    public CityWeatherListAdapter(Context context, List<Weather> weathers) {
        this.context = context;
        this.weathers = weathers;
    }

    @Override
    public int getCount() {
        return weathers.size();
    }

    @Override
    public Weather getItem(int position) {
        return weathers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_city_weather, null);
            holder = new ViewHolder();
            holder.cityName = convertView.findViewById(R.id.cityName);
            holder.weatherIcon = convertView.findViewById(R.id.weatherIcon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Weather weather = weathers.get(position);
        holder.cityName.setText(weather.getCity());
        holder.weatherIcon.setImageResource(Utils.getWeatherIcon(weather.getWeather()));

        return convertView;
    }

    private class ViewHolder {
        TextView cityName;
        ImageView weatherIcon;
    }
}
