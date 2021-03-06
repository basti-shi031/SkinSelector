package com.basti.skinselector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zhy.changeskin.SkinManager;

public class MainActivity extends AppCompatActivity {

    private Button bt_day, bt_night, bt_normal;
    private TextView tv_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SkinManager.getInstance().register(this);

        initView();

        initEvent();

    }

    private void initEvent() {
        bt_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinManager.getInstance().changeSkin("day");
            }
        });

        bt_night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinManager.getInstance().changeSkin("night");
            }
        });

        bt_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinManager.getInstance().removeAnySkin();
            }
        });

        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });

    }

    private void initView() {

        bt_day = (Button) findViewById(R.id.day);
        bt_night = (Button) findViewById(R.id.night);
        bt_normal = (Button) findViewById(R.id.normal);

        tv_next = (TextView) findViewById(R.id.next);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinManager.getInstance().unregister(this);
    }
}
