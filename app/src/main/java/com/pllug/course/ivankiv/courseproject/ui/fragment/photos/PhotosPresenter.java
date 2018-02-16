package com.pllug.course.ivankiv.courseproject.ui.fragment.photos;


import android.util.Log;


import com.pllug.course.ivankiv.courseproject.data.model.Photo;
import com.pllug.course.ivankiv.courseproject.data.source.interfaces.PhotoDataSource;
import com.pllug.course.ivankiv.courseproject.data.source.photos.PhotoRepository;

import java.util.List;

/**
 * Created by iw97d on 29.01.2018.
 */

public class PhotosPresenter implements PhotosContract.Presenter {

    private PhotosContract.View view;
    private PhotoRepository photoRepository;

    public PhotosPresenter(PhotoRepository photoRepository, PhotosContract.View view) {
        this.view = view;
        this.photoRepository = photoRepository;
    }

    @Override
    public void onGetPhotos() {
        int albumId = view.getAlbumId();
        view.showProgress();

        photoRepository.getPhotos(albumId, new PhotoDataSource.LoadPhotoCallback() {
            @Override
            public void onPhotoLoaded(List<Photo> photos) {
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
