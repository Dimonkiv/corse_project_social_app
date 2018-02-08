package com.pllug.course.ivankiv.courseproject.Model.data.source;


import com.pllug.course.ivankiv.courseproject.Model.data.source.interfaces.PhotoDataSource;

/**
 * Created by iw97d on 29.01.2018.
 */

public class PhotoRepository implements PhotoDataSource {
    private static PhotoRepository instance;
    private RetrofitPhotoDataSource retrofitPhotoDataSource;

    public PhotoRepository() {
        retrofitPhotoDataSource = new RetrofitPhotoDataSource();
    }

    public static PhotoRepository getInstance() {
        if(instance == null) {
            instance = new PhotoRepository();
        }

        return instance;
    }

    @Override
    public void getPhotos(int albumId, LoadPhotoCallback callback) {
        retrofitPhotoDataSource.getPhotos(albumId, callback);
    }
}
