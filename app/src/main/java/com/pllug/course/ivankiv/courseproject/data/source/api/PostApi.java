package com.pllug.course.ivankiv.courseproject.data.source.api;

import com.pllug.course.ivankiv.courseproject.data.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by iw97d on 31.01.2018.
 */

public interface PostApi {
    @GET("/posts/")
    Call<List<Post>> loadPosts(@Query("userId") int userId);
}
