package com.pllug.course.ivankiv.courseproject.Model.data.source;

import android.util.Log;

import com.pllug.course.ivankiv.courseproject.Model.data.model.User;
import com.pllug.course.ivankiv.courseproject.Model.data.source.api.UserApi;
import com.pllug.course.ivankiv.courseproject.Model.data.source.interfaces.UserDataSource;

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

public class RetrofitUserDataSource implements UserDataSource {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final String TAG = "myLog";

    UserApi userApi;

    public RetrofitUserDataSource() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userApi = retrofit.create(UserApi.class);
    }

    @Override
    public void getUser(int userId, final LoadUserCallback callback) {
        Call<List<User>> call = userApi.loadUser(userId);

        Log.d("myLog", "RetrofitDataSource зайшло у getUser");
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response != null && response.body() != null) {
                    Log.d("myLog", "RetrofitDataSource успіх");
                    callback.onUserLoaded(response.body());
                } else {
                    Log.d("myLog", "RetrofitDataSource провал");
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                callback.onFailure();
                Log.d("myLog", "RetrofitDataSource провал" + t.getMessage());

            }
        });
    }

    @Override
    public void getUsers(final LoadUserCallback callback) {
        Call<List<User>> call = userApi.loadUsers();

        Log.d("myLog", "RetrofitDataSource зайшло у getUser");
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response != null && response.body() != null) {
                    Log.d("myLog", "RetrofitDataSource успіх");
                    callback.onUserLoaded(response.body());
                } else {
                    Log.d("myLog", "RetrofitDataSource провал");
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                callback.onFailure();
                Log.d("myLog", "RetrofitDataSource провал" + t.getMessage());

            }
        });
    }
}
