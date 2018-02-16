package com.pllug.course.ivankiv.courseproject.data.source.album;


import com.pllug.course.ivankiv.courseproject.data.source.RetrofitInitialization;
import com.pllug.course.ivankiv.courseproject.data.source.api.SocialApi;
import com.pllug.course.ivankiv.courseproject.data.source.interfaces.AlbumDataSource;
import com.pllug.course.ivankiv.courseproject.data.model.Album;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by iw97d on 29.01.2018.
 */

public class RetrofitAlbumDataSource extends RetrofitInitialization implements AlbumDataSource {

    SocialApi socialApi;

    public RetrofitAlbumDataSource() {
         super();
         socialApi = retrofit.create(SocialApi.class);
    }


    @Override
    public void getAlbums(int userId, final LoadAlbumsCallback callback) {
        Call<List<Album>> call = socialApi.loadAlbum(userId);

        //асинхронний виклик
        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                if (response != null && response.body() != null) {
                    callback.onAlbumLoaded(response.body());
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                callback.onFailure();
            }
        });
    }



}
