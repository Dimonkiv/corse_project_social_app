package com.pllug.course.ivankiv.courseproject.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pllug.course.ivankiv.courseproject.R;
import com.pllug.course.ivankiv.courseproject.RegistrationActivity;

/**
 * Created by iw97d on 12.01.2018.
 */

public class SignInFragment extends Fragment implements View.OnClickListener {
    View root;
    private EditText login, password;
    private Button btn;
    private TextView goToForgotPassword, goToSignUp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.sign_in_fragment, container, false);

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
                checkingData();
                break;
            case R.id.go_to_forgot_password:
                ((RegistrationActivity)getActivity()).showForgotPasswordFragment();
                break;
            case R.id.go_to_sign_up:
                ((RegistrationActivity)getActivity()).showSignUpFragment();
                break;

        }
    }

    private void checkingData() {
        String loginStr, passwordStr;
        loginStr = login.getText().toString();
        passwordStr = password.getText().toString();

        boolean isEmpty = false;
        if (loginStr.isEmpty()) isEmpty = true;
        else isEmpty = false;
        if (passwordStr.isEmpty()) isEmpty = true;
        else isEmpty = false;

        if (isEmpty) Toast.makeText(getActivity(), "Enter data!", Toast.LENGTH_LONG).show();
        else ((RegistrationActivity)getActivity()).goToPersonActivity(loginStr, passwordStr);
    }
}
