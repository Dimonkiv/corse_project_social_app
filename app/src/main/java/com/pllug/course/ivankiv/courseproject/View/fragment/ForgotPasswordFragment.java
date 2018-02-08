package com.pllug.course.ivankiv.courseproject.View.fragment;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pllug.course.ivankiv.courseproject.Presenter.ForgotPasswordPresenter;
import com.pllug.course.ivankiv.courseproject.R;
import com.pllug.course.ivankiv.courseproject.View.Activity.RegistrationActivity;
import com.pllug.course.ivankiv.courseproject.View.Interface.ContractForgotPassword;

/**
 * Created by iw97d on 13.01.2018.
 */

public class ForgotPasswordFragment extends Fragment implements ContractForgotPassword.view {
    View root;
    private EditText email;
    private Button btn;
    ForgotPasswordPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.forgot_password_fragment, container, false);
        //Прив’язка xml елементів до java коду
        initView();
        //Обробник кліку
        initListener();
        return root;
    }

    private void initListener() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkingData();
            }
        });

    }

    private void initView() {
        email = (EditText)root.findViewById(R.id.forgot_password_email);
        btn = (Button)root.findViewById(R.id.forgot_password_btn);

        presenter = new ForgotPasswordPresenter(this);
    }

    private void checkingData() {
        String emailStr;
        emailStr = email.getText().toString();

        presenter.chechingDataForRegretPassword(emailStr);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void goToSignInFragment() {
        ((RegistrationActivity)getActivity()).showSignInFragment();
    }
}
