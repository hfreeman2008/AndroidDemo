package com.hxm.demo.autoscrollviewpager;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hxm.demo.R;

import java.util.ArrayList;
import java.util.List;

public class AutoScrollViewPaperActivity extends AppCompatActivity {

    private AutoScrollViewPager autoScrollViewPager ;
    private CustomPagerAdapter pagerAdapter;
    private List<Drawable> drawbleDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_scroll_view_paper);
        init();
    }

    private void init() {
        drawbleDatas = new ArrayList<Drawable>();
//        drawbleDatas.add(getResources().getDrawable(R.drawable.show_img_01));
//        drawbleDatas.add(getResources().getDrawable(R.drawable.show_img_02));
//        drawbleDatas.add(getResources().getDrawable(R.drawable.show_img_03));
//        drawbleDatas.add(getResources().getDrawable(R.drawable.show_img_04));
//        drawbleDatas.add(getResources().getDrawable(R.drawable.show_img_05));

        drawbleDatas.add(getResources().getDrawable(R.drawable.banner1));
        drawbleDatas.add(getResources().getDrawable(R.drawable.banner2));
        drawbleDatas.add(getResources().getDrawable(R.drawable.banner3));
        drawbleDatas.add(getResources().getDrawable(R.drawable.banner4));

        pagerAdapter = new CustomPagerAdapter(drawbleDatas,this);

        autoScrollViewPager = (AutoScrollViewPager)findViewById(R.id.view_pager);
        autoScrollViewPager.setAdapter(pagerAdapter);

        autoScrollViewPager.startAutoScroll();
        autoScrollViewPager.setInterval(2000);
        autoScrollViewPager.setCycle(true);
        autoScrollViewPager.setSwipeScrollDurationFactor(1);
        autoScrollViewPager.setAutoScrollDurationFactor(0.01);
    }

    private class  CustomPagerAdapter extends PagerAdapter{

        private List<Drawable> drawbleDatas;
        private List<View> views = new ArrayList<View>();
        private Context context;
        private LayoutInflater inflater;

        public CustomPagerAdapter() {
            super();
            init();
        }

        public CustomPagerAdapter(List<Drawable> drawbleDatas, Context context) {
            super();
            this.drawbleDatas = drawbleDatas;
            this.context = context;
            init();
        }

        private void init(){
            inflater = getLayoutInflater();
            views.clear();
        }

        @Override
        public int getCount() {
            return drawbleDatas.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //return super.instantiateItem(container, position);
            View view = inflater.inflate(R.layout.layout_view_paper_item, null);
            ImageView imageView = (ImageView)view.findViewById(R.id.image_view_item);
            imageView.setTag(position);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                imageView.setBackground(drawbleDatas.get(position));
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(),
                                "click:"+v.getTag(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }

            LinearLayout ll = (LinearLayout)view.findViewById(R.id.ll);
            ll.removeAllViews();
            for (int i = 0; i < drawbleDatas.size(); i++) {
                ImageView dotImage = new ImageView(getApplicationContext());

                if(i == position){
                    dotImage.setBackgroundDrawable(getResources().getDrawable(R.drawable.red_bg));
                }else{
                    dotImage.setBackgroundDrawable(getResources().getDrawable(R.drawable.blue_bg));
                }
                ll.addView(dotImage);
            }
            views.add(view);
            container.addView(view, 0);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView(views.get(position));
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}