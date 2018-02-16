package com.pllug.course.ivankiv.courseproject.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.pllug.course.ivankiv.courseproject.R;
import com.pllug.course.ivankiv.courseproject.ui.activity.registration.RegistrationActivity;

public class MainActivity extends AppCompatActivity {

    ImageView logo;
    TextView welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initAnimation();
        goToNextActivity();
    }

    private void initAnimation() {
        //Робимо анімацію
        Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.flash_anim);

        logo.startAnimation(myAnim);
        welcome.startAnimation(myAnim);
    }

    private void initView() {
        logo = findViewById(R.id.main_activity_logo);
        welcome = findViewById(R.id.main_activity_welcome);
    }

    private void goToNextActivity() {
        final Intent intent = new Intent(this, RegistrationActivity.class);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                }  catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(intent);
                    finish();
                }
            }
        };

        timer.start();
    }


}
