package com.pllug.course.ivankiv.courseproject.ui.fragment.photos;


import com.pllug.course.ivankiv.courseproject.data.model.Photo;

import java.util.List;

/**
 * Created by iw97d on 29.01.2018.
 */

public interface PhotosContract {
    interface View {
        void setPhotos(List<Photo> photos);

        void showProgress();

        void hideProgress();

        int getAlbumId();
    }

    interface Presenter {
        void onGetPhotos();
    }
}
