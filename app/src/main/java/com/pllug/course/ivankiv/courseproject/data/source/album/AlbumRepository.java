package com.pllug.course.ivankiv.courseproject.data.source.album;


import com.pllug.course.ivankiv.courseproject.data.source.interfaces.AlbumDataSource;

/**
 * Created by iw97d on 29.01.2018.
 */

public class AlbumRepository implements AlbumDataSource {

    private static AlbumRepository instance;
    private RetrofitAlbumDataSource retrofitAlbumDataSource;

    public AlbumRepository() {
        retrofitAlbumDataSource = new RetrofitAlbumDataSource();
    }

    public static AlbumRepository getInstance() {
        if(instance == null) {
            instance = new AlbumRepository();
        }

        return instance;
    }
    @Override
    public void getAlbums(int userId, LoadAlbumsCallback callback) {
        retrofitAlbumDataSource.getAlbums(userId, callback);
    }
}
