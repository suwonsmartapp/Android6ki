package com.example.weather;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.weather.adapters.WeatherRecyclerAdapter;
import com.example.weather.models.forecast.Forecast;
import com.example.weather.retrofit.WeatherUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by junsuk on 2017. 9. 8..
 */

public class ForecastFragment2 extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.city_edit_text)
    EditText mCityEditText;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    private WeatherUtil mWeatherUtil;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forecast2, container, false);
        unbinder = ButterKnife.bind(this, view);

        mCityEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_ENTER
                        && keyEvent.getAction() == KeyEvent.ACTION_UP) {
                    // 검색버튼을 땔 때
                    search(mCityEditText.getText().toString());

                    dismissKeyboard();
                    return true;
                }
                return false;
            }
        });

        RecyclerView.LayoutManager manager = new LinearLayoutManager(container.getContext());
        mRecyclerView.setLayoutManager(manager);

        // 경계선 넣기
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        return view;
    }

    private void dismissKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(mCityEditText.getWindowToken(), 0);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mWeatherUtil = new WeatherUtil();
    }

    private void search(String cityName) {
        mProgressBar.setVisibility(View.VISIBLE);

        // openweather
        mWeatherUtil.getApiService().getForecast(cityName).enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                WeatherRecyclerAdapter adapter = new WeatherRecyclerAdapter(getContext(), response.body().getList());
                mRecyclerView.setAdapter(adapter);

                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
