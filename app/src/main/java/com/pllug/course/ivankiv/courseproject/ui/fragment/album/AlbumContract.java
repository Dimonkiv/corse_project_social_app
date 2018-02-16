package com.pllug.course.ivankiv.courseproject.ui.fragment.album;

import com.pllug.course.ivankiv.courseproject.data.model.Album;
import com.pllug.course.ivankiv.courseproject.data.model.Photo;

import java.util.List;

/**
 * Created by iw97d on 29.01.2018.
 */

public interface AlbumContract {
    interface View {
        void setAlbums(List<Album> albums);

        void setPhotos(List<Photo> photos);

        void showProgress();

        void hideProgress();

        int getUserId();

    }

    interface Presenter {
        void onGetAlbums();

        void onGetPhotos(int albumId);
    }
}
