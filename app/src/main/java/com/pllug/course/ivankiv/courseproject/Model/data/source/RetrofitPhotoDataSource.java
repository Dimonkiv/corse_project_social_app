package com.pllug.course.ivankiv.courseproject.Model.data.source;



import com.pllug.course.ivankiv.courseproject.Model.data.model.PhotoModel;
import com.pllug.course.ivankiv.courseproject.Model.data.source.api.PhotoApi;
import com.pllug.course.ivankiv.courseproject.Model.data.source.interfaces.PhotoDataSource;

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

public class RetrofitPhotoDataSource implements PhotoDataSource {


    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final String TAG = "myLog";

    PhotoApi photoApi;

    public RetrofitPhotoDataSource() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        photoApi = retrofit.create(PhotoApi.class);
    }

    @Override
    public void getPhotos(int albumId, final LoadPhotoCallback callback) {
        Call<List<PhotoModel>> call = photoApi.loadPhoto(albumId);

        call.enqueue(new Callback<List<PhotoModel>>() {
            @Override
            public void onResponse(Call<List<PhotoModel>> call, Response<List<PhotoModel>> response) {
                if (response != null && response.body() != null) {
                    callback.onPhotoLoaded(response.body());
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<List<PhotoModel>> call, Throwable t) {
                callback.onFailure();
            }
        });

    }
}
