package com.pllug.course.ivankiv.courseproject.data.source.photos;



import com.pllug.course.ivankiv.courseproject.data.model.Photo;
import com.pllug.course.ivankiv.courseproject.data.source.RetrofitInitialization;
import com.pllug.course.ivankiv.courseproject.data.source.api.PhotoApi;
import com.pllug.course.ivankiv.courseproject.data.source.interfaces.PhotoDataSource;

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

public class RetrofitPhotoDataSource extends RetrofitInitialization implements PhotoDataSource {

    private PhotoApi photoApi;

    public RetrofitPhotoDataSource() {
        super();

        photoApi = retrofit.create(PhotoApi.class);
    }

    @Override
    public void getPhotos(int albumId, final LoadPhotoCallback callback) {
        Call<List<Photo>> call = photoApi.loadPhoto(albumId);

        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response != null && response.body() != null) {
                    callback.onPhotoLoaded(response.body());
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                callback.onFailure();
            }
        });

    }
}
