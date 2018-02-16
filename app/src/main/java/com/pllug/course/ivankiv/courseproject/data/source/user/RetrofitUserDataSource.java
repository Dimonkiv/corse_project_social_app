package com.pllug.course.ivankiv.courseproject.data.source.user;

import android.util.Log;

import com.pllug.course.ivankiv.courseproject.data.model.User;
import com.pllug.course.ivankiv.courseproject.data.source.RetrofitInitialization;
import com.pllug.course.ivankiv.courseproject.data.source.api.UserApi;
import com.pllug.course.ivankiv.courseproject.data.source.interfaces.UserDataSource;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by iw97d on 30.01.2018.
 */

public class RetrofitUserDataSource extends RetrofitInitialization implements UserDataSource {

    private UserApi userApi;

    public RetrofitUserDataSource() {
        super();
        userApi = retrofit.create(UserApi.class);
    }

    @Override
    public void getUser(int userId, final UserDataSource.LoadUserCallback callback) {
        Call<List<User>> call = userApi.loadUser(userId);

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response != null && response.body() != null) {
                    callback.onUserLoaded(response.body());
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                callback.onFailure();
            }
        });
    }

    @Override
    public void getUsers(final LoadUserCallback callback) {
        Call<List<User>> call = userApi.loadUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response != null && response.body() != null) {
                    callback.onUserLoaded(response.body());
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                callback.onFailure();
            }
        });
    }
}
