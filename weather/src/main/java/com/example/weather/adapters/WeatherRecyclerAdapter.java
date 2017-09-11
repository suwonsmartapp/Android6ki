package com.example.weather.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weather.R;
import com.example.weather.models.forecast.List;

/**
 * Created by junsuk on 2017. 9. 11..
 */

public class WeatherRecyclerAdapter extends RecyclerView.Adapter<WeatherRecyclerAdapter.ViewHolder> {

    private final java.util.List<List> mWeatherList;
    private Context mContext;

    public WeatherRecyclerAdapter(Context context, java.util.List<List> weatherList) {
        mWeatherList = weatherList;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        List weatherList = mWeatherList.get(position);

        String iconUrl = "http://openweathermap.org/img/w/" +
                weatherList.getWeather().get(0).getIcon() + ".png";
        Glide.with(mContext).load(iconUrl).into(holder.icon);
        holder.temp.setText("기온 : " + weatherList.getMain().getTemp());
        holder.min.setText("최저 기온 : " + weatherList.getMain().getTempMin());
        holder.max.setText("기온 : " + weatherList.getMain().getTempMax());
        holder.weather.setText("날씨 : " + weatherList.getWeather().get(0).getMain());
        holder.time.setText("시간 : " + weatherList.getDtTxt());
    }

    @Override
    public int getItemCount() {
        return mWeatherList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView temp;
        TextView min;
        TextView max;
        TextView weather;
        TextView time;

        ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.icon_image_view);
            temp = itemView.findViewById(R.id.temp_text_view);
            min = itemView.findViewById(R.id.mim_temp_text_view);
            max = itemView.findViewById(R.id.max_temp_text_view);
            weather = itemView.findViewById(R.id.weather_text_view);
            time = itemView.findViewById(R.id.time_text_view);
        }
    }
}
