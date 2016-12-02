package com.hxm.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.hxm.demo.circularimageview.CircularImageViewActivity;
import com.hxm.demo.swipelistview.SwipeMainActivity;
import com.hxm.demo.autoscrollviewpager.AutoScrollViewPaperActivity;
import com.hxm.demo.pulltorefresh.PullToRefreshActivity;

public class MainActivity extends AppCompatActivity {

    private Button button_swip_menu_listview;
    private Button button_circle_view;
    private Button button_auto_scroll_view_paper;
    private Button button_pull_to_refresh;
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

        button_auto_scroll_view_paper = (Button)findViewById(R.id.button_auto_scroll_view_paper);
        button_auto_scroll_view_paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(),AutoScrollViewPaperActivity.class));
            }
        });

        button_pull_to_refresh = (Button)findViewById(R.id.button_pull_to_refresh);
        button_pull_to_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(),PullToRefreshActivity.class));
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