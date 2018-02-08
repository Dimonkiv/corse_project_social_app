package com.pllug.course.ivankiv.courseproject.View.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.pllug.course.ivankiv.courseproject.Model.data.model.PhotoModel;
import com.pllug.course.ivankiv.courseproject.Model.data.source.PhotoRepository;
import com.pllug.course.ivankiv.courseproject.Presenter.PhotoPresenter;
import com.pllug.course.ivankiv.courseproject.R;
import com.pllug.course.ivankiv.courseproject.View.Interface.PhotoContract;
import com.pllug.course.ivankiv.courseproject.View.adapter.ImageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iw97d on 30.01.2018.
 */

public class PhotosFargment extends Fragment implements PhotoContract.View {

    private View root;
    private PhotoRepository photoRepository;
    private PhotoContract.Presenter presenter;
    private List<String> urls;
    private GridView gridView;
    private ImageAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.photos_fragment, container, false);

        initView();
        initData();
        initAdapter();
        initPresenter();
        presenter.onGetPhotos();

        return root;
    }
    private void initView() {
        gridView = root.findViewById(R.id.gridView);
    }

    private void initData() {
        photoRepository = PhotoRepository.getInstance();
    }

    private void initAdapter() {
        urls = new ArrayList<>();
        adapter = new ImageAdapter(getActivity(), urls);
        gridView.setAdapter(adapter);
    }


    private void initPresenter() {
        presenter = new PhotoPresenter(photoRepository, this);
    }



    @Override
    public void setPhotos(List<PhotoModel> photos) {
        for (PhotoModel photo : photos) {
            urls.add(photo.getUrl());
        }
        adapter.notifyDataSetChanged();
    }

    private void showProgressLoaderWithBackground(boolean visibility, String text) {
        if (text == null)
            text = "";
        ((TextView)root.findViewById(R.id.progress_bar_text)).setText(text);

        root.findViewById(R.id.container_progress_bar).setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showProgress() {
        showProgressLoaderWithBackground(true, " loading data...");
    }

    @Override
    public void hideProgress() {
        showProgressLoaderWithBackground(false, " loading data...");
    }
    @Override
    public int getAlbumId() {
        return 3;
    }
}
