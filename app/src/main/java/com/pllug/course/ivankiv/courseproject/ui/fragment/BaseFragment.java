package com.pllug.course.ivankiv.courseproject.ui.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by iw97d on 15.02.2018.
 */

public class BaseFragment {
    private TextView progressText;
    private LinearLayout container;

    public BaseFragment(TextView progressText, LinearLayout container) {
        this.progressText = progressText;
        this.container = container;
    }

    private void showProgressLoaderWithBackground(boolean visibility, String text) {
        if (text == null)
            text = "";
        progressText.setText(text);

        container.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    public void showProgress() {
        showProgressLoaderWithBackground(true, " loading data...");
    }


    public void hideProgress() {
        showProgressLoaderWithBackground(false, " loading data...");
    }
}
