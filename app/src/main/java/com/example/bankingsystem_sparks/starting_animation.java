package com.example.bankingsystem_sparks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Timer;
import java.util.TimerTask;

public class starting_animation extends AppCompatActivity {
    LottieAnimationView animation;
    ImageView start_background;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_animation);
        animation = findViewById(R.id.starting_screen_animation);
        start_background = findViewById(R.id.startingScreen_image);
        start_background.animate().translationY(-1600).setDuration(1000).setStartDelay(4000);
        animation.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent(starting_animation.this, Home_screen.class);
                startActivity(i);
                finish();
            }
        }, 5000);

    }
}