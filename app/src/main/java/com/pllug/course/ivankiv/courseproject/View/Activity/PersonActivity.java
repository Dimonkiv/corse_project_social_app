package com.pllug.course.ivankiv.courseproject.View.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pllug.course.ivankiv.courseproject.Model.data.model.PhotoModel;
import com.pllug.course.ivankiv.courseproject.Model.data.model.User;
import com.pllug.course.ivankiv.courseproject.R;
import com.pllug.course.ivankiv.courseproject.View.CircularTransformation;
import com.pllug.course.ivankiv.courseproject.View.fragment.AlbumFragment;
import com.pllug.course.ivankiv.courseproject.View.fragment.PhotosFargment;
import com.pllug.course.ivankiv.courseproject.View.fragment.PostsFragment;
import com.pllug.course.ivankiv.courseproject.View.fragment.ProfileFragment;
import com.pllug.course.ivankiv.courseproject.View.fragment.TodosFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PersonActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToogle;
    private int userId;

    private ImageView avatar;
    private TextView name, email;

    private String nameStr, urlStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        getUserIdFromIntent();
        showProfileFragment();
        openCloseMenuOnButton();
    }

    private void getUserIdFromIntent() {
        Intent intent = getIntent();
        userId = intent.getIntExtra("userId", 1);
    }

    private void openCloseMenuOnButton() {
        mDrawerLayout = findViewById(R.id.drawer);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        setupDrawerContent(navigationView);
    }
    public void  selectIterDrawer(MenuItem menuItem) {
        Fragment mFragment = null;
        Bundle args = new Bundle();
        args.putInt("userId", userId);

        switch(menuItem.getItemId()) {
            case R.id.profile:
                mFragment = new ProfileFragment();
                mFragment.setArguments(args);
                break;
            case R.id.alboms:
                mFragment = new AlbumFragment();
                mFragment.setArguments(args);
                break;
            case R.id.posts:
                args.putString("name", nameStr);
                args.putString("url", urlStr);
                mFragment = new PostsFragment();
                mFragment.setArguments(args);
                break;
            case R.id.todos:
                mFragment = new TodosFragment();
                mFragment.setArguments(args);
                break;
            case R.id.logout:
                Intent intent = new Intent(this, RegistrationActivity.class);
                startActivity(intent);
            default:
                mFragment = new ProfileFragment();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_for_person_fragment, mFragment).commit();
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawerLayout.closeDrawers();

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectIterDrawer(item);
                return true;
            }
        });
    }

    public void showPhotosFragment() {replaceFragment(new PhotosFargment());}
    public void showProfileFragment() {
        ProfileFragment profileFragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putInt("userId", userId);
        profileFragment.setArguments(args);
        addFragment(profileFragment);
    }

    public void setUserAndUrl(User user, String avatarUrl) {
        nameStr = user.getName();
        urlStr = avatarUrl;
        setHeaderDrawerData(user, avatarUrl);
    }

    private void setHeaderDrawerData(User user, String avatarUrl) {
        NavigationView nv = findViewById(R.id.navigation_view);
        View header = nv.getHeaderView(0);

        avatar = header.findViewById(R.id.header_avatar);
        name = header.findViewById(R.id.header_name);
        email = header.findViewById(R.id.header_email);

        showAvatar(avatarUrl);
        name.setText(user.getName());
        email.setText(user.getEmail());



    }
    private void showAvatar(String avatarUrl) {
        Picasso.with(this)
                .load(avatarUrl)
                .transform(new CircularTransformation(225))
                .into(avatar);
    }

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
