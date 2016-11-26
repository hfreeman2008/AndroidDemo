package com.hxm.demo.swipelistview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.hxm.demo.R;

public class SwipeMainActivity extends AppCompatActivity {

    private Button button_simple;
    private Button button_diff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_main);
        init();
    }

    private void init() {
        button_simple = (Button)findViewById(R.id.button_simple);
        button_simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(),SimpleActivity.class));
            }
        });

        button_diff = (Button)findViewById(R.id.button_diff);
        button_diff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(),DifferentMenuActivity.class));
            }
        });
    }
}
