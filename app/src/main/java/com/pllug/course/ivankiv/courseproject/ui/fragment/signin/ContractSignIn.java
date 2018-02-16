package com.pllug.course.ivankiv.courseproject.ui.fragment.signin;

/**
 * Created by iw97d on 19.01.2018.
 */

public interface ContractSignIn {
    public interface view {
        public void showToast(String message);
        public void goToProfileActivity();
    }
    public interface presenter {
        public void chekingData(String login, String password);
    }
}
