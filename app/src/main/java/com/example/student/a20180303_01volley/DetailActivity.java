package com.example.student.a20180303_01volley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by student on 2018/3/3.
 */

public class DetailActivity extends AppCompatActivity {
    TextView tv, tv2, tv3, tv4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        House h = (House) getIntent().getSerializableExtra("mydata");
        tv = findViewById(R.id.textView);
        tv2 = findViewById(R.id.textView2);
        tv3 = findViewById(R.id.textView3);
        tv4 = findViewById(R.id.textView4);
        tv.setText(h.district);
        tv2.setText(h.address);
        tv3.setText(h.tel);
        tv4.setText(h.opening_hours);
    }
}
