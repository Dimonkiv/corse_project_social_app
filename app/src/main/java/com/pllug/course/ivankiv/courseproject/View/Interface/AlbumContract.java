package com.pllug.course.ivankiv.courseproject.View.Interface;



import com.pllug.course.ivankiv.courseproject.Model.data.model.AlbumModel;
import com.pllug.course.ivankiv.courseproject.Model.data.model.PhotoModel;

import java.util.List;

/**
 * Created by iw97d on 29.01.2018.
 */

public interface AlbumContract {
    interface View {
        void setAlbums(List<AlbumModel> albums);

        void setPhotos(List<PhotoModel> photos);

        void showProgress();

        void hideProgress();

        int getUserId();

    }

    interface Presenter {
        void onGetAlbums();

        void onGetPhotos(int albumId);
    }
}
