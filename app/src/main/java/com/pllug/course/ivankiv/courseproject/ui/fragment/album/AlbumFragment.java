package com.pllug.course.ivankiv.courseproject.ui.fragment.album;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.pllug.course.ivankiv.courseproject.data.model.Album;
import com.pllug.course.ivankiv.courseproject.data.model.Photo;
import com.pllug.course.ivankiv.courseproject.data.source.album.AlbumRepository;
import com.pllug.course.ivankiv.courseproject.data.source.photos.PhotoRepository;
import com.pllug.course.ivankiv.courseproject.R;
import com.pllug.course.ivankiv.courseproject.ui.activity.ProfileActivity;
import com.pllug.course.ivankiv.courseproject.ui.adapter.AlbumAdapter;
import com.pllug.course.ivankiv.courseproject.ui.fragment.BaseFragment;

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
    private BaseFragment baseFragment;

    private ListView listView;
    private AlbumAdapter albumAdapter;
    private List<String> albumNames;
    private List<String> photoList;
    private List<Integer> photoCountList;
    private LinearLayout progressContainer;
    private TextView progressText;
    private int albumsCount;
    private int userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.albom_fragment, container, false);

        initView();
        initBaseFragment();
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
        progressContainer = root.findViewById(R.id.container_progress_bar);
        progressText = root.findViewById(R.id.progress_bar_text);
    }

    private void initBaseFragment() {
        baseFragment = new BaseFragment(progressText, progressContainer);
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
                ((ProfileActivity)getActivity()).showPhotosFragment();
            }
        });
    }

    private void getUserIdFromPersonActivity() {
        Bundle bundle = getArguments();
        userId = bundle.getInt("userId");
    }

    @Override
    public void setAlbums(List<Album> albums) {
        albumsCount = albums.size();
        for(Album album : albums) {
            albumNames.add(album.getTitle());
            presenter.onGetPhotos(album.getId());
        }
    }


    @Override
    public void setPhotos(List<Photo> photos) {
        photoList.add(photos.get(0).getUrl());
        photoCountList.add(photos.size());

        if(photoList.size() == albumsCount) {
            albumAdapter.notifyDataSetChanged();
        }
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
    public int getUserId() {
        return userId;
    }
}
