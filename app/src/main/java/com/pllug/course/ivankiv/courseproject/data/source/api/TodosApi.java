package com.pllug.course.ivankiv.courseproject.data.source.api;

import com.pllug.course.ivankiv.courseproject.data.model.Todo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by iw97d on 04.02.2018.
 */

public interface TodosApi {
    @GET("/todos/")
    Call<List<Todo>> loadTodos(@Query("userId") int userId);
}
