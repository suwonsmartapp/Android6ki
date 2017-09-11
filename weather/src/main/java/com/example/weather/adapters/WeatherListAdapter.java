package com.example.weather.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weather.R;
import com.example.weather.models.forecast.List;

/**
 * Created by junsuk on 2017. 9. 11..
 */

public class WeatherListAdapter extends BaseAdapter {

    private final java.util.List<List> mWeatherList;

    public WeatherListAdapter(java.util.List<List> weatherList) {
        mWeatherList = weatherList;
    }

    @Override
    public int getCount() {
        return mWeatherList.size();
    }

    @Override
    public Object getItem(int position) {
        return mWeatherList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_weather, viewGroup, false);
            holder.icon = convertView.findViewById(R.id.icon_image_view);
            holder.temp = convertView.findViewById(R.id.temp_text_view);
            holder.min = convertView.findViewById(R.id.mim_temp_text_view);
            holder.max = convertView.findViewById(R.id.max_temp_text_view);
            holder.weather = convertView.findViewById(R.id.weather_text_view);
            holder.time = convertView.findViewById(R.id.time_text_view);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        List weatherList = mWeatherList.get(position);

        String iconUrl = "http://openweathermap.org/img/w/" +
                weatherList.getWeather().get(0).getIcon() + ".png";
        Glide.with(viewGroup.getContext()).load(iconUrl).into(holder.icon);
        holder.temp.setText("기온 : " + weatherList.getMain().getTemp());
        holder.min.setText("최저 기온 : " + weatherList.getMain().getTempMin());
        holder.max.setText("기온 : " + weatherList.getMain().getTempMax());
        holder.weather.setText("날씨 : " + weatherList.getWeather().get(0).getMain());
        holder.time.setText("시간 : " + weatherList.getDtTxt());

        return convertView;
    }

    private static class ViewHolder {
        TextView temp;
        TextView min;
        TextView max;
        TextView weather;
        TextView time;

        ImageView icon;
    }
}
