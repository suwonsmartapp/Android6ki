package com.example.myapplication.adapterview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.myapplication.R;

import java.util.ArrayList;

public class AdapterViewExamActivity extends AppCompatActivity {

    private static final String TAG = AdapterViewExamActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_view_exam);

        // View
        ListView listView = (ListView) findViewById(R.id.list_view);
        GridView gridView = (GridView) findViewById(R.id.grid_view);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Data
        ArrayList<People> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int picture;
            if (i % 2 == 0) {
                picture = R.drawable.girl;
            } else {
                picture = R.mipmap.ic_launcher;
            }
            People people = new People("아무개 " + i, "전화번호 " + i, picture);
            data.add(people);
        }

        // Adapter
        PeopleAdapter adapter = new PeopleAdapter(AdapterViewExamActivity.this,
                data);

        listView.setAdapter(adapter);

//        gridView.setAdapter(adapter);
//        spinner.setAdapter(adapter);


        // OnItemClickListener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                People people = (People) parent.getAdapter().getItem(position);
//                Toast.makeText(AdapterViewExamActivity.this, people.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onItemClick: " + people.toString());    // debug
                Log.e(TAG, "onItemClick: 에러 ");    // error
                Log.i(TAG, "onItemClick: 정보");    // information
                Log.w(TAG, "onItemClick: 경고");  // warning
            }
        });
    }
}
