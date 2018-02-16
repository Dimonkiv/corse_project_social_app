package com.pllug.course.ivankiv.courseproject.data.source.interfaces;



import com.pllug.course.ivankiv.courseproject.data.model.Photo;

import java.util.List;

/**
 * Created by iw97d on 29.01.2018.
 */

public interface PhotoDataSource {
    interface LoadPhotoCallback {

        void onPhotoLoaded(List<Photo> photos);

        void onFailure();
    }

    void getPhotos(int albumId, LoadPhotoCallback callback);
}
