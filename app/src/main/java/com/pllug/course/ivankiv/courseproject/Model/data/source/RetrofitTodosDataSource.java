package com.pllug.course.ivankiv.courseproject.Model.data.source;

import com.pllug.course.ivankiv.courseproject.Model.data.model.TodoModel;
import com.pllug.course.ivankiv.courseproject.Model.data.source.api.TodosApi;
import com.pllug.course.ivankiv.courseproject.Model.data.source.interfaces.TodosDataSource;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by iw97d on 04.02.2018.
 */

public class RetrofitTodosDataSource  implements TodosDataSource {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private TodosApi todosApi;

    public RetrofitTodosDataSource() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        todosApi = retrofit.create(TodosApi.class);
    }

    @Override
    public void getTodos(int userId, final LoadTodosCallback callback) {
        Call<List<TodoModel>> call = todosApi.loadTodos(userId);
        call.enqueue(new Callback<List<TodoModel>>() {
            @Override
            public void onResponse(Call<List<TodoModel>> call, Response<List<TodoModel>> response) {
                if (response != null && response.body() != null) {
                    callback.onTodosLoaded(response.body());
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<List<TodoModel>> call, Throwable t) {
                callback.onFailure();
            }
        });
    }
}
