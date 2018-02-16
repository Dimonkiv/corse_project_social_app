package com.pllug.course.ivankiv.courseproject.ui.fragment.profile;

import android.util.Log;

import com.pllug.course.ivankiv.courseproject.data.model.Album;
import com.pllug.course.ivankiv.courseproject.data.model.Photo;
import com.pllug.course.ivankiv.courseproject.data.model.User;
import com.pllug.course.ivankiv.courseproject.data.source.album.AlbumRepository;
import com.pllug.course.ivankiv.courseproject.data.source.interfaces.AlbumDataSource;
import com.pllug.course.ivankiv.courseproject.data.source.interfaces.PhotoDataSource;
import com.pllug.course.ivankiv.courseproject.data.source.interfaces.UserDataSource;
import com.pllug.course.ivankiv.courseproject.data.source.photos.PhotoRepository;
import com.pllug.course.ivankiv.courseproject.data.source.user.UserRepository;


import java.util.List;

/**
 * Created by iw97d on 30.01.2018.
 */

public class ProfilePresenter implements ProfileContract.Presenter {

    private ProfileContract.View view;
    private UserRepository userRepository;
    private AlbumRepository albumRepository;
    private PhotoRepository photoRepository;
    private int userId;


    public ProfilePresenter(UserRepository userRepository, AlbumRepository albumRepository, PhotoRepository photoRepository, ProfileContract.View view) {
        this.view = view;
        this.userRepository = userRepository;
        this.albumRepository = albumRepository;
        this.photoRepository = photoRepository;
    }

    @Override
    public void onGetUser() {
        userId = view.getUserId();
        view.showProgress();
        userRepository.getUser(userId, new UserDataSource.LoadUserCallback() {
            @Override
            public void onUserLoaded(List<User> users) {
                view.setUser(users);
            }

            @Override
            public void onFailure() {
                view.hideProgress();
            }
        });
    }

    @Override
    public void onGetAlbum() {
        albumRepository.getAlbums(userId, new AlbumDataSource.LoadAlbumsCallback() {
            @Override
            public void onAlbumLoaded(List<Album> albums) {
                view.setAlbum(albums);
            }

            @Override
            public void onFailure() {

            }
        });
    }

    @Override
    public void onGetPhotos() {
        int albumId = view.getAlbumId();

        photoRepository.getPhotos(albumId, new PhotoDataSource.LoadPhotoCallback() {
            @Override
            public void onPhotoLoaded(List<Photo> photos) {
                view.hideProgress();
                view.setPhotos(photos);
            }

            @Override
            public void onFailure() {

            }
        });

    }

}
