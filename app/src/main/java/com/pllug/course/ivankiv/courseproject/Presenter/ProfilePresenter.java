package com.pllug.course.ivankiv.courseproject.Presenter;

import android.util.Log;

import com.pllug.course.ivankiv.courseproject.Model.data.model.AlbumModel;
import com.pllug.course.ivankiv.courseproject.Model.data.model.PhotoModel;
import com.pllug.course.ivankiv.courseproject.Model.data.model.User;
import com.pllug.course.ivankiv.courseproject.Model.data.source.AlbumRepository;
import com.pllug.course.ivankiv.courseproject.Model.data.source.PhotoRepository;
import com.pllug.course.ivankiv.courseproject.Model.data.source.UserRepository;
import com.pllug.course.ivankiv.courseproject.Model.data.source.interfaces.AlbumDataSource;
import com.pllug.course.ivankiv.courseproject.Model.data.source.interfaces.PhotoDataSource;
import com.pllug.course.ivankiv.courseproject.Model.data.source.interfaces.UserDataSource;
import com.pllug.course.ivankiv.courseproject.View.Interface.ProfileContract;

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
                for(User user : users) {
                    Log.d("myLog", "name - " + user.getName());
                }


                view.setUser(users);
            }

            @Override
            public void onFailure() {
                view.hideProgress();
                Log.d("myLog", "Помилка!!!!");
            }
        });
    }

    @Override
    public void onGetAlbum() {
        albumRepository.getAlbums(userId, new AlbumDataSource.LoadAlbumsCallback() {
            @Override
            public void onAlbumLoaded(List<AlbumModel> albums) {
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
        Log.d("myLog", "albumId у onGetPhotos - " + albumId);
        photoRepository.getPhotos(albumId, new PhotoDataSource.LoadPhotoCallback() {
            @Override
            public void onPhotoLoaded(List<PhotoModel> photos) {
                view.hideProgress();
                view.setPhotos(photos);
            }

            @Override
            public void onFailure() {

            }
        });

    }

}
