package com.pllug.course.ivankiv.courseproject.View.Interface;

import com.pllug.course.ivankiv.courseproject.Model.data.model.AlbumModel;
import com.pllug.course.ivankiv.courseproject.Model.data.model.PhotoModel;
import com.pllug.course.ivankiv.courseproject.Model.data.model.User;

import java.util.List;

/**
 * Created by iw97d on 30.01.2018.
 */

public interface ProfileContract {

    interface View {

        void setUser(List<User> user);

        void setAlbum(List<AlbumModel> albums);

        void setPhotos(List<PhotoModel> photos);

        void showProgress();

        void hideProgress();

        int getUserId();

        int getAlbumId();
    }

    interface Presenter {
        void onGetUser();
        void onGetAlbum();
        void onGetPhotos();
    }
}
