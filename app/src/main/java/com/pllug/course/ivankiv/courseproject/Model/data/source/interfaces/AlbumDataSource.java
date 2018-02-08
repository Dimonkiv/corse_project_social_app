package com.pllug.course.ivankiv.courseproject.Model.data.source.interfaces;


import com.pllug.course.ivankiv.courseproject.Model.data.model.AlbumModel;

import java.util.List;

/**
 * Created by iw97d on 29.01.2018.
 */

public interface AlbumDataSource {
    interface LoadAlbumsCallback {
        void onAlbumLoaded(List<AlbumModel> albums);

        void onFailure();
    }


    void getAlbums(int userId, LoadAlbumsCallback callback);

}
