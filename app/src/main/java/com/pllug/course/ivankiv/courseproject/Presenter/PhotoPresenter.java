package com.pllug.course.ivankiv.courseproject.Presenter;


import android.util.Log;

import com.pllug.course.ivankiv.courseproject.Model.data.model.PhotoModel;
import com.pllug.course.ivankiv.courseproject.Model.data.source.PhotoRepository;
import com.pllug.course.ivankiv.courseproject.Model.data.source.interfaces.PhotoDataSource;
import com.pllug.course.ivankiv.courseproject.View.Interface.PhotoContract;

import java.util.List;

/**
 * Created by iw97d on 29.01.2018.
 */

public class PhotoPresenter implements PhotoContract.Presenter {

    private PhotoContract.View view;
    private PhotoRepository photoRepository;

    public PhotoPresenter(PhotoRepository photoRepository, PhotoContract.View view) {
        this.view = view;
        this.photoRepository = photoRepository;
    }

    @Override
    public void onGetPhotos() {
        int albumId = view.getAlbumId();
        view.showProgress();
        Log.d("bumId", " " + albumId);
        photoRepository.getPhotos(albumId, new PhotoDataSource.LoadPhotoCallback() {
            @Override
            public void onPhotoLoaded(List<PhotoModel> photos) {
                view.hideProgress();
                view.setPhotos(photos);
            }

            @Override
            public void onFailure() {
                view.hideProgress();
            }
        });

    }
}
