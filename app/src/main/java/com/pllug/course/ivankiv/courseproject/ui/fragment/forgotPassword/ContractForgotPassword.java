package com.pllug.course.ivankiv.courseproject.ui.fragment.forgotPassword;

/**
 * Created by iw97d on 22.01.2018.
 */

public interface ContractForgotPassword {
    interface view{
        public void showToast(String message);
        public void goToSignInFragment();
    }

    interface presenter {
        public void chechingDataForRegretPassword(String email);
    }
}
