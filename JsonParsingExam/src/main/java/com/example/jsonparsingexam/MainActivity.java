package com.example.jsonparsingexam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.result_text)
    TextView mResultText;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    private JsonSampleInterface mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JsonSampleInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mApiService = retrofit.create(JsonSampleInterface.class);
    }

    public void requestLocation(View view) {
        mProgressBar.setVisibility(View.VISIBLE);

        // enqueue 비동기로 네트워크 연결 요청
        mApiService.getLocation().enqueue(new Callback<Location>() {
            @Override
            public void onResponse(Call<Location> call, Response<Location> response) {
                // UI 스레드
                mResultText.setText(response.body().toString());
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Location> call, Throwable t) {
                // UI 스레드
                mResultText.setText(t.getLocalizedMessage());
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }
}
