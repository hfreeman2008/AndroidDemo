package com.hxm.demo.pulltorefresh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.hxm.demo.R;

//https://github.com/johannilsson/android-pulltorefresh

public class PullToRefreshActivity extends AppCompatActivity {

    private Button pull_to_refresh_1;
    private Button pull_to_refresh_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh);
        init();
    }

    private void init() {
        pull_to_refresh_1 = (Button)findViewById(R.id.pull_to_refresh_1);
        pull_to_refresh_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(),PullToRefreshActivity1.class));
            }
        });
        pull_to_refresh_2 = (Button)findViewById(R.id.pull_to_refresh_2);
        pull_to_refresh_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplication(),SwipeMainActivity.class));
            }
        });
    }
}
