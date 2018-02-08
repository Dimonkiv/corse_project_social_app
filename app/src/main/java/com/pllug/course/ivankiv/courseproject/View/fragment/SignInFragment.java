package com.pllug.course.ivankiv.courseproject.View.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pllug.course.ivankiv.courseproject.Presenter.SignInPresenter;
import com.pllug.course.ivankiv.courseproject.R;
import com.pllug.course.ivankiv.courseproject.View.Activity.RegistrationActivity;
import com.pllug.course.ivankiv.courseproject.View.Interface.ContractSignIn;

/**
 * Created by iw97d on 12.01.2018.
 */

public class SignInFragment extends Fragment implements View.OnClickListener, ContractSignIn.view {
    View root;
    private EditText login, password;
    private Button btn;
    private TextView goToForgotPassword, goToSignUp;
    private SignInPresenter presenter;
    private String loginStr, passwordStr;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.sign_in_fragment, container, false);
        presenter = new SignInPresenter(this);
        //Зв’язуємо xml елементи з кодом
        initView();
        return root;
    }



    private void initView() {
        login = (EditText)root.findViewById(R.id.sign_in_login);
        password = (EditText)root.findViewById(R.id.sign_in_password);

        btn = (Button)root.findViewById(R.id.sign_in_btn);
        btn.setOnClickListener(this);

        goToForgotPassword = (TextView) root.findViewById(R.id.go_to_forgot_password);
        goToSignUp = (TextView) root.findViewById(R.id.go_to_sign_up);
        goToForgotPassword.setOnClickListener(this);
        goToSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_btn:
                getDataFromEditText();
                break;
            case R.id.go_to_forgot_password:
                ((RegistrationActivity)getActivity()).showForgotPasswordFragment();
                break;
            case R.id.go_to_sign_up:
                ((RegistrationActivity)getActivity()).showSignUpFragment();
                break;

        }
    }

    private void getDataFromEditText() {
        loginStr = login.getText().toString();
        passwordStr = password.getText().toString();
        Log.d("mLog", "Отримали дані з EditText");
        presenter.chekingData(loginStr, passwordStr);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void goToProfileActivity() {
        Log.d("mLog", "Передаємо дані в актівіті");
        ((RegistrationActivity)getActivity()).getSignInDataFromFragment(loginStr, passwordStr);
    }
}
