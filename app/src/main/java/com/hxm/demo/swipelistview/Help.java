package com.hxm.demo.swipelistview;

import android.content.Context;
import android.util.TypedValue;

public class Help {

    public static int dp2px(int dp, Context context) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                context.getResources().getDisplayMetrics());
    }
}
