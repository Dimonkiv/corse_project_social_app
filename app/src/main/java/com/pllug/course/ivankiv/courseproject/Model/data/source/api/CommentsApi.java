package com.pllug.course.ivankiv.courseproject.Model.data.source.api;

import com.pllug.course.ivankiv.courseproject.Model.data.model.CommentModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by iw97d on 01.02.2018.
 */

public interface CommentsApi {
    @GET("/comments/")
    Call<List<CommentModel>> loadComments(@Query("postId") int postId);
}
