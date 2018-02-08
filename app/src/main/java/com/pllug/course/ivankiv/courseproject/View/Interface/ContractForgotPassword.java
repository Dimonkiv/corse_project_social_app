package com.pllug.course.ivankiv.courseproject.View.Interface;

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
