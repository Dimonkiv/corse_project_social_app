package com.pllug.course.ivankiv.courseproject.data.source.interfaces;

import com.pllug.course.ivankiv.courseproject.data.model.Todo;

import java.util.List;

/**
 * Created by iw97d on 04.02.2018.
 */

public interface TodosDataSource {
    interface LoadTodosCallback {

        void onTodosLoaded(List<Todo> todos);

        void onFailure();
    }

    void getTodos(int userId, LoadTodosCallback callback);
}
