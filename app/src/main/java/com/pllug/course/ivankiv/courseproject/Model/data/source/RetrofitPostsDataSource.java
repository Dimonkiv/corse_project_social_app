package com.pllug.course.ivankiv.courseproject.Model.data.source;

import com.pllug.course.ivankiv.courseproject.Model.data.model.PostModel;
import com.pllug.course.ivankiv.courseproject.Model.data.source.api.PostApi;
import com.pllug.course.ivankiv.courseproject.Model.data.source.interfaces.PostDataSource;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by iw97d on 31.01.2018.
 */

public class RetrofitPostsDataSource implements PostDataSource {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final String TAG = "myLog";

    PostApi postApi;

    public RetrofitPostsDataSource() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        postApi = retrofit.create(PostApi.class);
    }
    @Override
    public void getPosts(int userId, final LoadPostsCallback callback) {
        Call<List<PostModel>> call = postApi.loadPosts(userId);

        call.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                if (response != null && response.body() != null) {
                    callback.onPostsLoaded(response.body());
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                callback.onFailure();
            }
        });
    }
}
