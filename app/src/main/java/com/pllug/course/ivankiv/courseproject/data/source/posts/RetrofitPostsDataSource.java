package com.pllug.course.ivankiv.courseproject.data.source.posts;



import com.pllug.course.ivankiv.courseproject.data.model.Post;
import com.pllug.course.ivankiv.courseproject.data.source.RetrofitInitialization;
import com.pllug.course.ivankiv.courseproject.data.source.api.PostApi;
import com.pllug.course.ivankiv.courseproject.data.source.interfaces.PostDataSource;

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

public class RetrofitPostsDataSource extends RetrofitInitialization implements PostDataSource {

    private PostApi postApi;

    public RetrofitPostsDataSource() {
       super();

        postApi = retrofit.create(PostApi.class);
    }
    @Override
    public void getPosts(int userId, final LoadPostsCallback callback) {
        Call<List<Post>> call = postApi.loadPosts(userId);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response != null && response.body() != null) {
                    callback.onPostsLoaded(response.body());
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                callback.onFailure();
            }
        });
    }
}
