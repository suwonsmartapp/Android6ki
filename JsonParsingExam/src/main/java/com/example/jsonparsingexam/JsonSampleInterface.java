package com.example.jsonparsingexam;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by junsuk on 2017. 9. 7..
 */

public interface JsonSampleInterface {
    String BASE_URL = "https://gist.githubusercontent.com/junsuk5/30964c94a0fa1529314d9f884a783ae9/raw/b105e227a74c8e6b7f45de0c57b00df1963729fc/";

    @GET("location.json")
    Call<Location> getLocation();
}
