package com.example.jsonparsingexam;

import com.example.jsonparsingexam.models.Location;
import com.example.jsonparsingexam.models.Weather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by junsuk on 2017. 9. 7..
 */

public interface JsonSampleInterface {
    String BASE_URL = "https://gist.githubusercontent.com/junsuk5/";

    @GET("30964c94a0fa1529314d9f884a783ae9/raw/b105e227a74c8e6b7f45de0c57b00df1963729fc/location.json")
    Call<Location> getLocation();

    @GET("6b293ac781b038366419ac6e4027abb7/raw/afd3f207203d1e84b87f37ffc41809989428f0ec/weather.json")
    Call<List<Weather>> getWeatherList();
}
