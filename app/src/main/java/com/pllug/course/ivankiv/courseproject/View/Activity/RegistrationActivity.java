package com.pllug.course.ivankiv.courseproject.View.Activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.pllug.course.ivankiv.courseproject.Model.data.source.UserRepository;
import com.pllug.course.ivankiv.courseproject.Presenter.RegistrationActivityPresenter;
import com.pllug.course.ivankiv.courseproject.R;
import com.pllug.course.ivankiv.courseproject.View.Interface.RegistrationPresenterContractRegistrationActivity;
import com.pllug.course.ivankiv.courseproject.View.fragment.ForgotPasswordFragment;
import com.pllug.course.ivankiv.courseproject.View.fragment.SignInFragment;
import com.pllug.course.ivankiv.courseproject.View.fragment.SignUpFragment;

public class RegistrationActivity extends AppCompatActivity implements RegistrationPresenterContractRegistrationActivity.View {

    private RegistrationActivityPresenter presenter;
    private UserRepository userRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initData();
        initPresenter();
        presenter.onUsersLoaded();

        //Запускаємо фрагмент логування
        showSignInFragment();
    }

    private void initData() {
        userRepository = UserRepository.getInstance();
    }

    private void initPresenter() {
        presenter = new RegistrationActivityPresenter(userRepository ,this);
    }


    public void showSignUpFragment() {replaceFragment(new SignUpFragment());}
    public void showSignInFragment(){
        addFragment(new SignInFragment());
    }
    public void showForgotPasswordFragment() {replaceFragment(new ForgotPasswordFragment());}

    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
    private void addFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();
    }

    //Метод, який перевіряє чи логін і пароль правильні
    //Якщо так, то перехід на PersonActivity
    public void getSignInDataFromFragment(String login, String password) {
        Log.d("mLog", "Отримали дані з Fragment");
        presenter.checkSignInData(login, password);
    }
    @Override
    public void goToProfileActivity(int id) {
        Log.d("mLog", "Переходимо на нове актівіті");
        Intent intent = new Intent(RegistrationActivity.this, PersonActivity.class);
        intent.putExtra("userId", id);
        startActivity(intent);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
