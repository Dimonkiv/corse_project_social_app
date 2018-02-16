package com.pllug.course.ivankiv.courseproject.ui.fragment.signup;

/**
 * Created by iw97d on 19.01.2018.
 */

public interface SignUpContract {
     interface View {
         void showToast(String message);

         void showSignInFragment();
    }
     interface Presenter {
         void checkingData(String name,String surname,String email, String login, String password, String confirmPassword);
    }
}
