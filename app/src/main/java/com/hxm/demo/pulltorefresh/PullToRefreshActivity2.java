package com.hxm.demo.pulltorefresh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.hxm.demo.R;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

//https://github.com/liaohuqiu/android-Ultra-Pull-To-Refresh

public class PullToRefreshActivity2 extends AppCompatActivity {

    private PtrClassicFrameLayout ptrFrameLayout;
    private TextView textView;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh2);

        init();
    }

    private void init() {
        textView = (TextView)findViewById(R.id.textView);

        ptrFrameLayout = (PtrClassicFrameLayout)findViewById(R.id.fragment_ptr_home_ptr_frame);
        ptrFrameLayout.setLastUpdateTimeRelateObject(this);
        ptrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        update();
                        ptrFrameLayout.refreshComplete();
                    }
                }, 1500);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;
            }
        });
    }

    private void update() {
        count = count+1;
        textView.setText(getResources().getString(R.string.text_view_refresh)+count);
    }
}