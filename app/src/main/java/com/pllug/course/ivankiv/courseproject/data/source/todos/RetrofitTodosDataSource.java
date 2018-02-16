package com.pllug.course.ivankiv.courseproject.data.source.todos;



import com.pllug.course.ivankiv.courseproject.data.model.Todo;
import com.pllug.course.ivankiv.courseproject.data.source.RetrofitInitialization;
import com.pllug.course.ivankiv.courseproject.data.source.api.TodosApi;
import com.pllug.course.ivankiv.courseproject.data.source.interfaces.TodosDataSource;

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

public class RetrofitTodosDataSource extends RetrofitInitialization implements TodosDataSource {

    private TodosApi todosApi;

    public RetrofitTodosDataSource() {
        super();
        todosApi = retrofit.create(TodosApi.class);
    }

    @Override
    public void getTodos(int userId, final LoadTodosCallback callback) {
        Call<List<Todo>> call = todosApi.loadTodos(userId);
        call.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                if (response != null && response.body() != null) {
                    callback.onTodosLoaded(response.body());
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                callback.onFailure();
            }
        });
    }
}
