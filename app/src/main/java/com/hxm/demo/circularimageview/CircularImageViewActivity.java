package com.hxm.demo.circularimageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hxm.demo.R;

public class CircularImageViewActivity extends AppCompatActivity {

    private CircularImageView circularImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_image_view);
        init();
    }

    private void init() {
        circularImageView = (CircularImageView)findViewById(R.id.circularImageView);
        circularImageView.setBorderColor(getResources().getColor(R.color.colorgreen));
        circularImageView.setBorderWidth(5);
        circularImageView.addShadow();
    }
}
