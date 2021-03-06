package com.pllug.course.ivankiv.courseproject.ui.fragment.signin;

import android.util.Log;

import com.pllug.course.ivankiv.courseproject.ui.fragment.signin.ContractSignIn;

/**
 * Created by iw97d on 19.01.2018.
 */

public class SignInPresenter implements ContractSignIn.presenter{
    private ContractSignIn.view view;

    public SignInPresenter(ContractSignIn.view view) {
        this.view = view;
    }
    @Override
    public void chekingData(String login, String password) {
        boolean isEmpty = false;
        if (login.isEmpty()) isEmpty = true;
        else isEmpty = false;
        if (password.isEmpty()) isEmpty = true;
        else isEmpty = false;
        Log.d("mLog", "Перевірили чи поля не пусті");
        if (isEmpty) view.showToast("Enter data!");
        else view.goToProfileActivity();
    }
}
