package com.pllug.course.ivankiv.courseproject.Model.data.source.api;

import com.pllug.course.ivankiv.courseproject.Model.data.model.TodoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by iw97d on 04.02.2018.
 */

public interface TodosApi {
    @GET("/todos/")
    Call<List<TodoModel>> loadTodos(@Query("userId") int userId);
}
