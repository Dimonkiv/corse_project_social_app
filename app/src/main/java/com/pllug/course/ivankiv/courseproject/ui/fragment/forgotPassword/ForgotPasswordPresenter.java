package com.pllug.course.ivankiv.courseproject.ui.fragment.forgotPassword;

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
