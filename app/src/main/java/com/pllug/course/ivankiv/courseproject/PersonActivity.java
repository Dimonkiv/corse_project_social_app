package com.pllug.course.ivankiv.courseproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pllug.course.ivankiv.courseproject.fragment.ProfileFragment;

public class PersonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        showProfileFragment();
    }


    public void showProfileFragment() {addFragment(new ProfileFragment());}

    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.frame_for_person_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }
    private void addFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_for_person_fragment, fragment)
                .commit();
    }
}
