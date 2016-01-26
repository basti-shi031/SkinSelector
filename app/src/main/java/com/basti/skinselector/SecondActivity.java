package com.basti.skinselector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zhy.changeskin.SkinManager;

/**
 * Created by Bowen on 2016-01-26.
 */
public class SecondActivity extends AppCompatActivity {

    private Button bt_day, bt_night, bt_normal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

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
    }

    private void initView() {

        bt_day = (Button) findViewById(R.id.day);
        bt_night = (Button) findViewById(R.id.night);
        bt_normal = (Button) findViewById(R.id.normal);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinManager.getInstance().unregister(this);
    }
}
