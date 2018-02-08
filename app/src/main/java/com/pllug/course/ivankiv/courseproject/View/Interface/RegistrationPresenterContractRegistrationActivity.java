package com.pllug.course.ivankiv.courseproject.View.Interface;

/**
 * Created by iw97d on 19.01.2018.
 */

public interface RegistrationPresenterContractRegistrationActivity {
     interface View {
        void goToProfileActivity(int id);
        void showToast(String message);

    }

     interface Presenter {
        void checkSignInData(String login, String password);
        void onUsersLoaded();
    }
}
