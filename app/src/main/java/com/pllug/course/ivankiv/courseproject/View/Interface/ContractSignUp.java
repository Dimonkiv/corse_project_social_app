package com.pllug.course.ivankiv.courseproject.View.Interface;

/**
 * Created by iw97d on 19.01.2018.
 */

public interface ContractSignUp {
    public interface View {
        public void showToast(String message);
        public void showSignInFragment();
    }
    public interface Presenter {
        public void checkingData(String name,String surname,String email, String login, String password, String confirmPassword);
    }
}
