package com.pllug.course.ivankiv.courseproject.ui.fragment.profile;

import com.pllug.course.ivankiv.courseproject.data.model.Album;
import com.pllug.course.ivankiv.courseproject.data.model.Photo;
import com.pllug.course.ivankiv.courseproject.data.model.User;

import java.util.List;

/**
 * Created by iw97d on 30.01.2018.
 */

public interface ProfileContract {

    interface View {

        void setUser(List<User> user);

        void setAlbum(List<Album> albums);

        void setPhotos(List<Photo> photos);

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
