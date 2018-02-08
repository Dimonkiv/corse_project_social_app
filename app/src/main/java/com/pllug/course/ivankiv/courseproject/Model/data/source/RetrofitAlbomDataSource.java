package com.pllug.course.ivankiv.courseproject.Model.data.source;


import com.pllug.course.ivankiv.courseproject.Model.data.model.AlbumModel;
import com.pllug.course.ivankiv.courseproject.Model.data.source.api.SocialApi;
import com.pllug.course.ivankiv.courseproject.Model.data.source.interfaces.AlbumDataSource;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by iw97d on 29.01.2018.
 */

public class RetrofitAlbomDataSource implements AlbumDataSource {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final String TAG = "myLog";

    SocialApi socialApi;

    public RetrofitAlbomDataSource() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

         socialApi = retrofit.create(SocialApi.class);
    }


    @Override
    public void getAlbums(int userId, final LoadAlbumsCallback callback) {
        Call<List<AlbumModel>> call = socialApi.loadAlbum(userId);

        //асинхронний виклик
        call.enqueue(new Callback<List<AlbumModel>>() {
            @Override
            public void onResponse(Call<List<AlbumModel>> call, Response<List<AlbumModel>> response) {
                if (response != null && response.body() != null) {
                    callback.onAlbumLoaded(response.body());
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<List<AlbumModel>> call, Throwable t) {
                callback.onFailure();
            }
        });
    }



}
