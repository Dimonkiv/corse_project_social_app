package com.pllug.course.ivankiv.courseproject.ui.fragment.photos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pllug.course.ivankiv.courseproject.data.model.Photo;
import com.pllug.course.ivankiv.courseproject.data.source.photos.PhotoRepository;
import com.pllug.course.ivankiv.courseproject.R;
import com.pllug.course.ivankiv.courseproject.ui.adapter.ImageAdapter;
import com.pllug.course.ivankiv.courseproject.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iw97d on 30.01.2018.
 */

public class PhotosFargment extends Fragment implements PhotosContract.View {

    private View root;
    private PhotoRepository photoRepository;
    private PhotosContract.Presenter presenter;
    private List<String> urls;
    private GridView gridView;
    private ImageAdapter adapter;
    private BaseFragment baseFragment;
    private TextView progressText;
    private LinearLayout progressContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.photos_fragment, container, false);

        initView();
        initBaseFragment();
        initData();
        initAdapter();
        initPresenter();
        presenter.onGetPhotos();

        return root;
    }

    private void initView() {
        gridView = root.findViewById(R.id.gridView);
        progressText = root.findViewById(R.id.progress_bar_text);
        progressContainer = root.findViewById(R.id.container_progress_bar);
    }

    private void initBaseFragment() {
        baseFragment = new BaseFragment(progressText, progressContainer);
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
        presenter = new PhotosPresenter(photoRepository, this);
    }



    @Override
    public void setPhotos(List<Photo> photos) {
        for (Photo photo : photos) {
            urls.add(photo.getUrl());
        }
        adapter.notifyDataSetChanged();
    }


    @Override
    public void showProgress() {
        baseFragment.showProgress();
    }

    @Override
    public void hideProgress() {
        baseFragment.hideProgress();
    }
    @Override
    public int getAlbumId() {
        return 3;
    }
}
