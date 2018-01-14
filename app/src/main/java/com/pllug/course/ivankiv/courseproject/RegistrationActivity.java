package com.pllug.course.ivankiv.courseproject;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.pllug.course.ivankiv.courseproject.fragment.ForgotPasswordFragment;
import com.pllug.course.ivankiv.courseproject.fragment.SignInFragment;
import com.pllug.course.ivankiv.courseproject.fragment.SignUpFragment;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //Запускаємо фрагмент логування
        showSignInFragment();
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
    public void goToPersonActivity(String login, String password) {
        String trueLogin = "admin";
        String truePassword = "admin";
        boolean isEqual = false;

        /*if (login.equals(trueLogin)) {
            if(password.equals(truePassword)) {
                isEqual = true;
            }
        }

        if (isEqual) Toast.makeText(this, "Success Sign In!", Toast.LENGTH_LONG).show();
        else Toast.makeText(this, "Incorect login or password!", Toast.LENGTH_LONG).show();*/

        Intent intent = new Intent(RegistrationActivity.this, PersonActivity.class);
        startActivity(intent);
    }
}
