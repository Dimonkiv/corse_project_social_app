package com.pllug.course.ivankiv.courseproject.Presenter;


import android.util.Log;

import com.pllug.course.ivankiv.courseproject.Model.data.model.AlbumModel;
import com.pllug.course.ivankiv.courseproject.Model.data.model.PhotoModel;
import com.pllug.course.ivankiv.courseproject.Model.data.source.AlbumRepository;
import com.pllug.course.ivankiv.courseproject.Model.data.source.PhotoRepository;
import com.pllug.course.ivankiv.courseproject.Model.data.source.interfaces.AlbumDataSource;
import com.pllug.course.ivankiv.courseproject.Model.data.source.interfaces.PhotoDataSource;
import com.pllug.course.ivankiv.courseproject.View.Interface.AlbumContract;

import java.util.List;

/**
 * Created by iw97d on 29.01.2018.
 */

public class AlbumPresenter implements AlbumContract.Presenter {

    private AlbumContract.View view;
    private AlbumRepository albumRepository;
    private PhotoRepository photoRepository;

    public AlbumPresenter(AlbumRepository albumRepository, PhotoRepository photoRepository, AlbumContract.View view) {
        this.albumRepository = albumRepository;
        this.photoRepository = photoRepository;
        this.view = view;
    }

    @Override
    public void onGetAlbums() {
        int userId = view.getUserId();
        view.showProgress();
        albumRepository.getAlbums(userId, new AlbumDataSource.LoadAlbumsCallback() {
            @Override
            public void onAlbumLoaded(List<AlbumModel> albums) {
                for (AlbumModel album: albums) {
                    Log.d("Album user 5", "title - " + album.getTitle());
                }
                view.setAlbums(albums);
            }

            @Override
            public void onFailure() {
                view.hideProgress();
            }
        });

    }

    @Override
    public void onGetPhotos(int albumId) {
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
