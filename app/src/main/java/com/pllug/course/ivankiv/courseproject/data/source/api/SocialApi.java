package com.pllug.course.ivankiv.courseproject.data.source.api;



import com.pllug.course.ivankiv.courseproject.data.model.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by iw97d on 28.01.2018.
 */

public interface SocialApi {
    @GET("/albums/")
    Call<List<Album>> loadAlbum(@Query("userId") int userId);

   /* @GET("/photos/")
    Call<List<PhotoModel>> loadPhoto();*/
}
