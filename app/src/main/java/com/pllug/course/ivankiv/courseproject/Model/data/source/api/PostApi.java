package com.pllug.course.ivankiv.courseproject.Model.data.source.api;

import com.pllug.course.ivankiv.courseproject.Model.data.model.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by iw97d on 31.01.2018.
 */

public interface PostApi {
    @GET("/posts/")
    Call<List<PostModel>> loadPosts(@Query("userId") int userId);
}
