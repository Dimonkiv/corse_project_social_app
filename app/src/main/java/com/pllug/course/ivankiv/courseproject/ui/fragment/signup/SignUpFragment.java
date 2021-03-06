package com.pllug.course.ivankiv.courseproject.ui.fragment.signup;

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
import com.pllug.course.ivankiv.courseproject.ui.activity.registration.RegistrationActivity;

/**
 * Created by iw97d on 13.01.2018.
 */

public class SignUpFragment extends Fragment implements View.OnClickListener, SignUpContract.View {
    View root;
    private EditText name, surname,login, email, password, confirmPassword;
    private Button btn;
    private TextView goToSignIn, goToForgotPassword;
    private SignUpPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.sign_up_fragment, container, false);
        presenter = new SignUpPresenter(this);
        //Зв’язуємо xml елементи з кодом
        initView();
        return root;
    }


    private void checkingData() {
        String loginStr, passwordStr, confirmPasswordStr, nameStr, surnameStr, emailStr;

        nameStr = name.getText().toString();
        surnameStr = surname.getText().toString();
        emailStr = email.getText().toString();
        loginStr = login.getText().toString();
        passwordStr = password.getText().toString();
        confirmPasswordStr = confirmPassword.getText().toString();

        presenter.checkingData(nameStr, surnameStr, emailStr, loginStr, passwordStr, confirmPasswordStr);

    }
    private void initView() {
        name = (EditText)root.findViewById(R.id.sign_up_name);
        surname = (EditText)root.findViewById(R.id.sign_up_surname);
        login = (EditText)root.findViewById(R.id.sign_up_login);
        email = (EditText)root.findViewById(R.id.sign_up_email);
        password = (EditText)root.findViewById(R.id.sign_up_password);
        confirmPassword = (EditText)root.findViewById(R.id.sign_up_confirm_password);

        btn = (Button)root.findViewById(R.id.sign_up_btn);
        btn.setOnClickListener(this);

        goToSignIn = (TextView) root.findViewById(R.id.go_to_sign_in);
        goToSignIn.setOnClickListener(this);
        goToForgotPassword = (TextView)root.findViewById(R.id.go_to_forgot_password_from_sign_up);
        goToForgotPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_up_btn:
                checkingData();
                break;
            case R.id.go_to_sign_in:
                ((RegistrationActivity)getActivity()).showSignInFragment();
                break;
            case R.id.go_to_forgot_password_from_sign_up:
                ((RegistrationActivity)getActivity()).showForgotPasswordFragment();
                break;
        }

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSignInFragment() {
        ((RegistrationActivity)getActivity()).showSignInFragment();
    }
}
