package com.vandee.myappumb.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.vandee.myappumb.R;
import com.vandee.myappumb.intro.IntroActivity;
import com.vandee.myappumb.preference.PreferenceManager;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
    }

    public void loadSlide(View view){
        new PreferenceManager(this).clearPreference();
        startActivity(new Intent(this, IntroActivity.class));
        finish();
    }
}