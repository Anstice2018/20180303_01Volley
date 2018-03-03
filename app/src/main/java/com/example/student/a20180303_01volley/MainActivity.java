package com.example.student.a20180303_01volley;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayAdapter<String> adapter;
    ArrayList<String> data = new ArrayList<>();
    House newtpe[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView)findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, data);
        lv.setAdapter(adapter);

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        final StringRequest request = new StringRequest(
                "http://data.ntpc.gov.tw/od/data/api/BF90FA7E-C358-4CDA-B579-B6C84ADC96A1?$format=json",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("NET", response);
                        Gson gson = new Gson();
                        newtpe = gson.fromJson(response, House[].class);
                        for (House h : newtpe)
                        {
                            Log.d("NET", "2th : " + h.district);
                            data.add(h.district);
                        }
                        adapter.notifyDataSetChanged();
                    }
                }
                , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(MainActivity.this, DetailActivity.class);
                it.putExtra("mydata", newtpe[position]);
                startActivity(it);

            }
        });
        queue.add(request);
        queue.start();
    }
}
