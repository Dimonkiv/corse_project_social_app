package com.pllug.course.ivankiv.courseproject.data.source.comments;



import com.pllug.course.ivankiv.courseproject.data.model.Comment;
import com.pllug.course.ivankiv.courseproject.data.source.RetrofitInitialization;
import com.pllug.course.ivankiv.courseproject.data.source.api.CommentsApi;
import com.pllug.course.ivankiv.courseproject.data.source.interfaces.CommentsDataSource;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by iw97d on 01.02.2018.
 */

public class RetrofitCommentsDataSource extends RetrofitInitialization implements CommentsDataSource {

    CommentsApi commentsApi;
    public RetrofitCommentsDataSource () {
        super();
        commentsApi = retrofit.create(CommentsApi.class);
    }
    @Override
    public void getComments(int postId, final LoadedCommentsCallback callback) {
        Call<List<Comment>> call = commentsApi.loadComments(postId);

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response != null && response.body() != null) {
                    callback.onCommentsLoaded(response.body());
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                callback.onFailure();
            }
        });
    }
}
