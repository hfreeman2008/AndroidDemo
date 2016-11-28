package com.hxm.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.hxm.demo.circularimageview.CircularImageViewActivity;
import com.hxm.demo.swipelistview.SwipeMainActivity;

public class MainActivity extends AppCompatActivity {

    private Button button_swip_menu_listview;
    private Button button_circle_view;
    private Button button_test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        button_swip_menu_listview = (Button)findViewById(R.id.button_swip_menu_listview);
        button_swip_menu_listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(),SwipeMainActivity.class));
            }
        });

        button_circle_view = (Button)findViewById(R.id.button_circle_view);
        button_circle_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(),CircularImageViewActivity.class));
            }
        });

        button_test = (Button)findViewById(R.id.button_test);
        button_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplication(),CircularImageViewActivity.class));
            }
        });
    }
}