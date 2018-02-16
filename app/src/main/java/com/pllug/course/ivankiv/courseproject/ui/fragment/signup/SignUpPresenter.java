package com.pllug.course.ivankiv.courseproject.ui.fragment.signup;

import com.pllug.course.ivankiv.courseproject.ui.fragment.signup.SignUpContract;

/**
 * Created by iw97d on 19.01.2018.
 */

public class SignUpPresenter implements SignUpContract.Presenter {
    private SignUpContract.View view;

    public SignUpPresenter(SignUpContract.View view) {
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
