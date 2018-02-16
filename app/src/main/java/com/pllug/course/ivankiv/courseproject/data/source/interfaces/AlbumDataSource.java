package com.pllug.course.ivankiv.courseproject.data.source.interfaces;


import com.pllug.course.ivankiv.courseproject.data.model.Album;

import java.util.List;

/**
 * Created by iw97d on 29.01.2018.
 */

public interface AlbumDataSource {
    interface LoadAlbumsCallback {
        void onAlbumLoaded(List<Album> albums);

        void onFailure();
    }


    void getAlbums(int userId, LoadAlbumsCallback callback);

}
