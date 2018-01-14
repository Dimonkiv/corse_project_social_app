package com.pllug.course.ivankiv.courseproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pllug.course.ivankiv.courseproject.R;

/**
 * Created by iw97d on 14.01.2018.
 */

public class ProfileFragment extends Fragment {
    View root;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.profile_fragment, container, false);

        return root;
    }
}
