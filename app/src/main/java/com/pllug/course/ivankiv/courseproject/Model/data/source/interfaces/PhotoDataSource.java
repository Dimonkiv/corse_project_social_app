package com.pllug.course.ivankiv.courseproject.Model.data.source.interfaces;



import com.pllug.course.ivankiv.courseproject.Model.data.model.PhotoModel;

import java.util.List;

/**
 * Created by iw97d on 29.01.2018.
 */

public interface PhotoDataSource {
    interface LoadPhotoCallback {

        void onPhotoLoaded(List<PhotoModel> photos);

        void onFailure();
    }

    void getPhotos(int albumId, LoadPhotoCallback callback);
}
