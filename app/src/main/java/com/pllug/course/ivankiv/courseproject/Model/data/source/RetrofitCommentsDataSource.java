package com.pllug.course.ivankiv.courseproject.Model.data.source;

import com.pllug.course.ivankiv.courseproject.Model.data.model.CommentModel;
import com.pllug.course.ivankiv.courseproject.Model.data.source.api.CommentsApi;
import com.pllug.course.ivankiv.courseproject.Model.data.source.interfaces.CommentsDataSource;

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

public class RetrofitCommentsDataSource implements CommentsDataSource {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    CommentsApi commentsApi;
    public RetrofitCommentsDataSource () {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        commentsApi = retrofit.create(CommentsApi.class);
    }
    @Override
    public void getComments(int postId, final LoadedCommentsCallback callback) {
        Call<List<CommentModel>> call = commentsApi.loadComments(postId);

        call.enqueue(new Callback<List<CommentModel>>() {
            @Override
            public void onResponse(Call<List<CommentModel>> call, Response<List<CommentModel>> response) {
                if (response != null && response.body() != null) {
                    callback.onCommentsLoaded(response.body());
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<List<CommentModel>> call, Throwable t) {
                callback.onFailure();
            }
        });
    }
}
