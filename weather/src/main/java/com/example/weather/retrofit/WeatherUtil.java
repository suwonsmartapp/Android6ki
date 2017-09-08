package com.example.weather.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by junsuk on 2017. 9. 8..
 */

public class WeatherUtil {
    private final WeatherApi mApiService;

    public WeatherUtil() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WeatherApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mApiService = retrofit.create(WeatherApi.class);
    }

    public WeatherApi getApiService() {
        return mApiService;
    }
}
