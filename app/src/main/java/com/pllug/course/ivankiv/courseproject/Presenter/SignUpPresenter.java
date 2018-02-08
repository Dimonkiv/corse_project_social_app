package com.pllug.course.ivankiv.courseproject.Presenter;

import android.widget.Toast;

import com.pllug.course.ivankiv.courseproject.View.Activity.RegistrationActivity;
import com.pllug.course.ivankiv.courseproject.View.Interface.ContractSignUp;
import com.pllug.course.ivankiv.courseproject.View.fragment.SignUpFragment;

/**
 * Created by iw97d on 19.01.2018.
 */

public class SignUpPresenter implements ContractSignUp.Presenter {
    private ContractSignUp.View view;

    public SignUpPresenter(ContractSignUp.View view) {
        this.view = view;
    }

    @Override
    public void checkingData(String name,String surname, String email, String login, String password, String confirmPassword) {
        boolean isEmpty = false;
        if (name.isEmpty()) isEmpty = true;
        else isEmpty = false;
        if (email.isEmpty()) isEmpty = true;
        else isEmpty = false;
        if (surname.isEmpty()) isEmpty = true;
        else isEmpty = false;
        if (login.isEmpty()) isEmpty = true;
        else isEmpty = false;
        if (password.isEmpty()) isEmpty = true;
        else isEmpty = false;
        if (confirmPassword.isEmpty()) isEmpty = true;
        else isEmpty = false;

        if (isEmpty) view.showToast("Enter data!");
        else {
            view.showToast("Sign up");
            view.showSignInFragment();
        }
    }
}
