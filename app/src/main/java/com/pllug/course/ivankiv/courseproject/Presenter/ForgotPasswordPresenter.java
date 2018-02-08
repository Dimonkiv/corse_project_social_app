package com.pllug.course.ivankiv.courseproject.Presenter;

import android.widget.Toast;

import com.pllug.course.ivankiv.courseproject.View.Activity.RegistrationActivity;
import com.pllug.course.ivankiv.courseproject.View.Interface.ContractForgotPassword;

/**
 * Created by iw97d on 22.01.2018.
 */

public class ForgotPasswordPresenter implements ContractForgotPassword.presenter {
    private ContractForgotPassword.view view;

    public ForgotPasswordPresenter(ContractForgotPassword.view view) {
        this.view = view;
    }
    @Override
    public void chechingDataForRegretPassword(String email) {
        if (email.isEmpty()) view.showToast("Enter data!");
        else {
            view.showToast("Send new password");
            view.goToSignInFragment();
        }

    }
}
