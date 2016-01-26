package com.basti.skinselector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhy.changeskin.SkinManager;

public class MainActivity extends AppCompatActivity {

    private Button bt_day, bt_night, bt_normal;

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
