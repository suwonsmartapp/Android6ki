package com.example.weather.retrofit;

import com.example.weather.models.CurrentWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    String APP_ID = "95114a1e948559e010396b4debdf1672";

    @GET("weather?lang=kr&units=metric&appid=" + APP_ID)
    Call<CurrentWeather> getCurrentWeather(@Query("q") String cityName);
}