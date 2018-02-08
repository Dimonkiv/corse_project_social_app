package com.pllug.course.ivankiv.courseproject.View.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.pllug.course.ivankiv.courseproject.Model.data.model.AlbumModel;
import com.pllug.course.ivankiv.courseproject.Model.data.model.PhotoModel;
import com.pllug.course.ivankiv.courseproject.Model.data.source.AlbumRepository;
import com.pllug.course.ivankiv.courseproject.Model.data.source.PhotoRepository;
import com.pllug.course.ivankiv.courseproject.Presenter.AlbumPresenter;
import com.pllug.course.ivankiv.courseproject.R;
import com.pllug.course.ivankiv.courseproject.View.Activity.PersonActivity;
import com.pllug.course.ivankiv.courseproject.View.Interface.AlbumContract;
import com.pllug.course.ivankiv.courseproject.View.adapter.AlbumAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iw97d on 15.01.2018.
 */

public class AlbumFragment extends Fragment implements AlbumContract.View {
    View root;

    private AlbumRepository albumRepository;
    private PhotoRepository photoRepository;
    private AlbumContract.Presenter presenter;

    private ListView listView;
    private AlbumAdapter albumAdapter;
    private List<String> albumNames;
    private List<String> photoList;
    private List<Integer> photoCountList;
    private int albumsCount;
    private int userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.albom_fragment, container, false);

        initView();
        initData();
        initAdapter();
        initPresenter();
        initListener();
        getUserIdFromPersonActivity();
        presenter.onGetAlbums();

        return root;
    }

    private void initView() {
        listView = root.findViewById(R.id.list_view);
    }

    private void initData() {
        albumRepository = AlbumRepository.getInstance();
        photoRepository = PhotoRepository.getInstance();
    }

    private void initAdapter() {
        albumNames = new ArrayList<>();
        photoList = new ArrayList<>();
        photoCountList = new ArrayList<>();

        albumAdapter = new AlbumAdapter(getActivity(), albumNames, photoList, photoCountList);
        listView.setAdapter(albumAdapter);
    }

    private void initPresenter() {
        presenter = new AlbumPresenter(albumRepository, photoRepository, this);
    }


    private void initListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("myLog", "items " + id);
                ((PersonActivity)getActivity()).showPhotosFragment();
            }
        });
    }

    private void getUserIdFromPersonActivity() {
        Bundle bundle = getArguments();
        userId = bundle.getInt("userId");
    }

    @Override
    public void setAlbums(List<AlbumModel> albums) {
        albumsCount = albums.size();
        for(AlbumModel album : albums) {
            albumNames.add(album.getTitle());
            presenter.onGetPhotos(album.getId());
        }
    }


    @Override
    public void setPhotos(List<PhotoModel> photos) {
        photoList.add(photos.get(0).getUrl());
        photoCountList.add(photos.size());

        if(photoList.size() == albumsCount) {
            albumAdapter.notifyDataSetChanged();
        }
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
    public int getUserId() {
        return userId;
    }


}
