package com.pllug.course.ivankiv.courseproject.ui.fragment.album;


import android.util.Log;

import com.pllug.course.ivankiv.courseproject.data.model.Album;
import com.pllug.course.ivankiv.courseproject.data.model.Photo;
import com.pllug.course.ivankiv.courseproject.data.source.album.AlbumRepository;
import com.pllug.course.ivankiv.courseproject.data.source.interfaces.AlbumDataSource;
import com.pllug.course.ivankiv.courseproject.data.source.interfaces.PhotoDataSource;
import com.pllug.course.ivankiv.courseproject.data.source.photos.PhotoRepository;

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
            public void onAlbumLoaded(List<Album> albums) {
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
