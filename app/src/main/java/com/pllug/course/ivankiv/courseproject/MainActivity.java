package com.pllug.course.ivankiv.courseproject;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView logo;
    TextView welcome, description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Зв’язуємо xml елементи з кодом
        logo = (ImageView)findViewById(R.id.main_activity_logo);

        welcome = (TextView)findViewById(R.id.main_activity_welcome);
        description = (TextView)findViewById(R.id.main_activity_description);

        //Робимо анімацію
        Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.flash_anim);

        logo.startAnimation(myAnim);
        welcome.startAnimation(myAnim);
        description.startAnimation(myAnim);

        //Запускаємо нове актівіті через заданий час
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
