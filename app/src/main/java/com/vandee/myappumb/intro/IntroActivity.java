package com.vandee.myappumb.intro;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;
import com.vandee.myappumb.adapter.MpagerAdapter;

import com.vandee.myappumb.R;

public class IntroActivity extends AppCompatActivity {

    //Set variable pager
    private ViewPager mPager;
    private int[] layouts = {R.layout.first_slide, R.layout.second_slide, R.layout.third_slide};
    private MpagerAdapter mpagerAdapter;

    //Set variable dots
    private LinearLayout Dots_layout;
    private ImageView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_intro);

        mPager = (ViewPager) findViewById(R.id.viewPager);
        mpagerAdapter = new MpagerAdapter(layouts, this);
        mPager.setAdapter(mpagerAdapter);

        Dots_layout = (LinearLayout) findViewById(R.id.dotslayout);

        createDots(0);
    }

    private void createDots(int current_position){
        if(Dots_layout != null)
            Dots_layout.removeAllViews();

        dots = new ImageView[layouts.length];
        for(int i = 0; i < layouts.length; i++){
            dots[i] = new ImageView(this);
            if(i == current_position){
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dots));
            }else {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.default_dots));
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4, 0, 4, 0);

            Dots_layout.addView(dots[i], params);
        }
    }
}