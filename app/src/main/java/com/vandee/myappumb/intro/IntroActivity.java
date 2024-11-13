package com.vandee.myappumb.intro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.vandee.myappumb.login.LoginActivity;
import com.vandee.myappumb.preference.PreferenceManager;

public class IntroActivity extends AppCompatActivity implements View.OnClickListener{

    //Set variable pager
    private ViewPager mPager;
    private int[] layouts = {R.layout.first_slide, R.layout.second_slide, R.layout.third_slide};
    private MpagerAdapter mpagerAdapter;

    //Set variable dots
    private LinearLayout Dots_layout;
    private ImageView[] dots;

    //Set variable Skip & Next
    private Button btnNext, btnSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_intro);

        if(new PreferenceManager(this).checkPreference()){
            loadHome();
        }

        mPager = (ViewPager) findViewById(R.id.viewPager);
        mpagerAdapter = new MpagerAdapter(layouts, this);
        mPager.setAdapter(mpagerAdapter);

        Dots_layout = (LinearLayout) findViewById(R.id.dotslayout);
        btnSkip = (Button) findViewById(R.id.btnSkip);
        btnNext = (Button) findViewById(R.id.btnNext);

        btnSkip.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        createDots(0);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                createDots(position);
                if(position == layouts.length - 1){
                    btnNext.setText("Start");
                    btnSkip.setVisibility(View.INVISIBLE);
                }else {
                    btnNext.setText("Next");
                    btnSkip.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnNext){
            loadNextSlide();
        }else {
            loadHome();
            new PreferenceManager(this).writePreference();
        }
    }

    private void loadHome(){
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void loadNextSlide(){
        int next_slide = mPager.getCurrentItem()+1;
        if(next_slide < layouts.length){
            mPager.setCurrentItem(next_slide);
        }else {
            loadHome();
            new PreferenceManager(this).writePreference();
        }
    }
}