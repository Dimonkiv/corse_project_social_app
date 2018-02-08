package com.pllug.course.ivankiv.courseproject.Model.data.source;


import com.pllug.course.ivankiv.courseproject.Model.data.source.interfaces.AlbumDataSource;

/**
 * Created by iw97d on 29.01.2018.
 */

public class AlbumRepository implements AlbumDataSource {

    private static AlbumRepository instance;
    private RetrofitAlbomDataSource retrofitAlbomDataSource;

    public AlbumRepository() {
        retrofitAlbomDataSource = new RetrofitAlbomDataSource();
    }

    public static AlbumRepository getInstance() {
        if(instance == null) {
            instance = new AlbumRepository();
        }

        return instance;
    }
    @Override
    public void getAlbums(int userId, LoadAlbumsCallback callback) {
        retrofitAlbomDataSource.getAlbums(userId, callback);
    }
}
