package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private List<Map<String, Object>> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.list_view);

        // data
        mDataList = new ArrayList<>();

        addItem("커피 앱", "Intent, Activity", CoffeeActivity.class);

        SimpleAdapter adapter = new SimpleAdapter(this,
                mDataList,
                android.R.layout.simple_list_item_2,
                new String[]{"title", "description"},
                new int[]{android.R.id.text1, android.R.id.text2});

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Class cls = (Class) mDataList.get(position).get("class");
                Intent intent = new Intent(MainActivity.this, cls);
                startActivity(intent);
            }
        });
    }

    private void addItem(String title, String description, Class cls) {
        Map<String, Object> data = new HashMap<>();
        data.put("title", title);
        data.put("description", description);
        data.put("class", cls);

        mDataList.add(data);
    }
}
