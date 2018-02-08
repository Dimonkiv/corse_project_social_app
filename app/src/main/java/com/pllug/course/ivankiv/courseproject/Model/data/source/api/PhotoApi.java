package com.pllug.course.ivankiv.courseproject.Model.data.source.api;


import com.pllug.course.ivankiv.courseproject.Model.data.model.PhotoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by iw97d on 28.01.2018.
 */

public interface PhotoApi {
    @GET("/photos/")
    Call<List<PhotoModel>> loadPhoto(@Query("albumId") int albumId);
}
